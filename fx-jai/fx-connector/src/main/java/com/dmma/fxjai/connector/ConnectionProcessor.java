package com.dmma.fxjai.connector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;

import com.dmma.fxjai.connector.errors.ConnectionError;
import com.dmma.fxjai.connector.types.IncomeMsgType;
import com.dmma.fxjai.connector.types.OutcomeMsgType;
import com.dmma.fxjai.core.entities.BarDTO;
import com.dmma.fxjai.core.services.MetaTraderService;
import com.dmma.fxjai.core.types.AccountType;
import com.dmma.fxjai.core.types.PeriodType;
import com.dmma.fxjai.core.types.SymbolType;

public class ConnectionProcessor implements Runnable{
	public static final String SEPARATOR = ";";
	private Socket fromClientSocket;
	private ConnectorStatus connectorStatus;
	private Logger log;
	private BufferedWriter  toClient;
	private BufferedReader  fromClient;
	private MetaTraderService metaTraderService;

	public ConnectionProcessor(MetaTraderService metaTraderService, Socket fromClientSocket,ConnectorStatus connectorStatus, Logger log) {
		this.fromClientSocket = fromClientSocket;
		this.connectorStatus  = connectorStatus;
		this.log              = log;
		this.metaTraderService = metaTraderService;
	}


	@Override
	public void run() {
		this.connectorStatus.connectionProcessStarted();
		try {
			toClient   = new BufferedWriter(new OutputStreamWriter(fromClientSocket.getOutputStream()));
			fromClient = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

			// any message from client starts with client account id
			String msgFromClient   = fromClient.readLine();	

			String messageArray[] = msgFromClient.split(SEPARATOR);
			String msgTypeStr     = messageArray[0];

			IncomeMsgType msgType = IncomeMsgType.findById(msgTypeStr);
			switch (msgType) {
			case isPing:
				processPing(messageArray);
				break;
			case isRegistration:
				processRegistration(messageArray);
				break;
			case isActual:
				processActual(messageArray);
				break;
			case isUpdateRequest:
				processUpdateRequest(messageArray);
				break;
			case isBarUpdate:
				processBarUpdate(messageArray);
				break;
			}

			this.connectorStatus.connectionProcessSucceed();
		}catch (IOException e) {
			this.connectorStatus.connectionProcessFailed();
			log.error("Connection failed ...");
			e.printStackTrace();
		}catch (ConnectionError e) {
			this.connectorStatus.connectionProcessRejected();
			log.warn("Connection rejected - account: " + e.getLogin() + ", reason: "+e.getReason());
			sendMessage("Connection rejected - account: " + e.getLogin() + ", reason: "+e.getReason());
		}finally{
			try{
				toClient.flush();
				fromClient.close();
				toClient.close();
				fromClientSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}

	/**
	 * {@link IncomeMsgType#isPing}
	 * */  
	private void processPing(String[] messageArray) throws IOException {
		String pingText  = messageArray[1];
		log.info("Ping from client:" +pingText);

		StringBuilder pongText = new StringBuilder(OutcomeMsgType.isPong.getId());
		pongText.append(SEPARATOR);
		pongText.append("Hello, you sent me <");
		pongText.append(pingText);
		pongText.append(">");

		//out = "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
		//out += "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
		//out += "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";

		sendMessage(pongText.toString());
		sendMessage("hehe");
	}

	/**
	 * {@link IncomeMsgType#isRegistration}
	 * */  
	private void processRegistration(String[] messageArray) throws IOException, ConnectionError {
		String account  = messageArray[1];
		String type     = messageArray[2];
		String userName = messageArray[3];
		String server   = messageArray[4];

		AccountType accountType = AccountType.findByName(type);

		int accountId = metaTraderService.processRegistration(account, accountType, userName, server);

		StringBuilder response = new StringBuilder(OutcomeMsgType.isRegistrationStatus.getId());
		response.append(SEPARATOR);
		response.append(accountId);
		sendMessage(response.toString());
	}

	/**
	 * {@link IncomeMsgType#isActual}
	 * */        
	private void processActual(String[] messageArray) throws IOException, ConnectionError{
		String account    = messageArray[1];
		Integer accountId = Integer.valueOf(messageArray[2]);
		String symbolStr = messageArray[3];
		String dateLongString = messageArray[4];
		String bidStr    = messageArray[5];

		SymbolType symbol = SymbolType.findByStr(symbolStr.replace(".",""));
		Double bid =Double.valueOf(bidStr);
		Date  date = new Date(Long.valueOf(dateLongString));
		metaTraderService.setActual(account, accountId, symbol, bid, date);

	}

	/** <table>
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|accountId|SymbolType</td></tr>
	 *  <tr><td><b>example:  </b></td><td>           05| 123456|       12|    USDCHF</td></tr>
	 *  </table>
	 */
	private void processUpdateRequest(String[] messageArray) throws IOException, ConnectionError{
		String account   = messageArray[1];
		Integer accountId = Integer.valueOf(messageArray[2]);
		SymbolType symbol = SymbolType.findByStr(messageArray[3]);
		
		BarDTO barMN = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.isMN);
		BarDTO barW1 = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.isMN);
		BarDTO barD1 = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.isMN);
		
		StringBuilder response = new StringBuilder();
		response.append(OutcomeMsgType.isUpdateResponce.getId());
		response.append(SEPARATOR);
		response.append(symbol.name());
		response.append(SEPARATOR);
		response.append(barMN==null?"0":barMN.getDate().getTime());
		response.append(SEPARATOR);
		response.append(barW1==null?"0":barW1.getDate().getTime());
		response.append(SEPARATOR);
		response.append(barD1==null?"0":barD1.getDate().getTime());
		sendMessage(response.toString());
	}
		
	
	// IncomeMsgType|account|accountId|SymbolType|         time|PeriodType|O|H|L|C|V</td></tr>
	//            04| 123456|       12|    USDCHF|1316087684225|      1440|x|x|x|x|x</td></tr>
	/**
	 * {@link IncomeMsgType#isBarUpdate}
	 * */  
	private void processBarUpdate(String[] messageArray) throws IOException, ConnectionError{
		BarDTO bar = new BarDTO();
		String account   = messageArray[1];
		Integer accountId = Integer.valueOf(messageArray[2]);
		bar.setClientId(accountId);
		
		SymbolType symbol = SymbolType.findByStr(messageArray[3]);
		bar.setSymbolId(symbol.getId());
		bar.setDate(new Date(Long.valueOf(messageArray[4])));
		PeriodType period = PeriodType.findById(messageArray[5]);
		bar.setPeriod(period.getId());
		bar.setOpen(  Double.valueOf(messageArray[6]));
		bar.setHigh(  Double.valueOf(messageArray[7]));
		bar.setLow(   Double.valueOf(messageArray[8]));
		bar.setClose( Double.valueOf(messageArray[9]));
		bar.setVolume(Integer.valueOf(messageArray[10]));

		metaTraderService.updateBar(account, bar);

	}

	void sendMessage(String msg){
		try{
			toClient.write(msg+"!");
			toClient.newLine();
			System.out.println("server -> client: " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

}
