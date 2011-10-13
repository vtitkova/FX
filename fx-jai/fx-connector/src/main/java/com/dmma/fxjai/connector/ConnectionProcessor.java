package com.dmma.fxjai.connector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;

import com.dmma.fxjai.connector.types.IncomeMsgType;
import com.dmma.fxjai.connector.types.OutcomeMsgType;
import com.dmma.fxjai.core.services.MetaTraderService;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.types.AccountType;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;

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
			// 4;293165;1;EURUSD.;43200;978307200;0.9421;0.9599;0.9113;0.9379;4931
			// any message from client starts with client account id
			String msgFromClient   = fromClient.readLine();
			String msgToClient = "";
			
			String messageArray[] = msgFromClient.split(SEPARATOR);
			String msgTypeStr     = messageArray[0];

			IncomeMsgType msgType = IncomeMsgType.findById(msgTypeStr);
			if(msgType != null){
				switch (msgType) {
				case isPing:
					msgToClient = processPing(messageArray);
					break;
				case isRegistration:
					msgToClient = processRegistration(messageArray);
					break;
				case isLastBarRequest:
					msgToClient = processLastBarRequest(messageArray);
					break;
				case isBarUpdate:
					msgToClient = processBarUpdate(messageArray);
					break;
				case isActual:
					msgToClient = processActual(messageArray);
					break;
				}
			}

			
			toClient.write(msgToClient+"!");
			toClient.newLine();
			toClient.flush();
			System.out.println("server->client: " + msgToClient);

			this.connectorStatus.connectionProcessSucceed();
		}catch (IOException e) {
			this.connectorStatus.connectionProcessFailed();
			log.error("Connection failed ...");
			e.printStackTrace();
		}finally{
			try{
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
	private String processPing(String[] messageArray) throws IOException {
		String pingText  = messageArray[1];
		log.info("Ping from client:" +pingText);

		StringBuilder response = new StringBuilder();
		response.append(OutcomeMsgType.isPong.getId());
		response.append(SEPARATOR);
		response.append("Hello, you sent me <");
		response.append(pingText);
		response.append(">");
		return response.toString();
	}

	/**
	 * {@link IncomeMsgType#isRegistration}
	 * */  
	private String processRegistration(String[] messageArray) throws IOException {
		String account  = messageArray[1];
		String type     = messageArray[2];
		String userName = messageArray[3];
		String server   = messageArray[4];

		AccountType accountType = AccountType.findByName(type);

		int accountId = metaTraderService.processRegistration(account, accountType, userName, server);

		StringBuilder response = new StringBuilder();
		response.append(OutcomeMsgType.isRegistrationStatus.getId());
		response.append(SEPARATOR);
		response.append(accountId);
		return response.toString();  
	}

	/**
	 * {@link IncomeMsgType#isActual}
	 * */        
	private String processActual(String[] messageArray) throws IOException{
		String account    = messageArray[1];
		Integer accountId = Integer.valueOf(messageArray[2]);
		String symbolStr = messageArray[3];
		String dateLongString = messageArray[4].trim();
		String bidStr    = messageArray[5].trim();

		SymbolType symbol = SymbolType.findByStr(symbolStr.replace(".",""));
		Double bid =Double.valueOf(bidStr);
		Date  date = new Date(Long.valueOf(dateLongString));
		metaTraderService.setActual(account, accountId, symbol, bid, date);
		return "";
	}

	/** <table>
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|accountId|SymbolType</td></tr>
	 *  <tr><td><b>example:  </b></td><td>           03| 123456|       12|    USDCHF</td></tr>
	 *  </table>
	 */
	private String processLastBarRequest(String[] messageArray) throws IOException{
		String account   = messageArray[1].trim();
		Integer accountId = Integer.valueOf(messageArray[2].trim());
		SymbolType symbol = SymbolType.findByStr(messageArray[3].replace(".","").trim());

		BarDTO barMN  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.MN1);
		BarDTO barW1  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.W1);
		BarDTO barD1  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.D1);
		BarDTO barH4  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.H4);
		BarDTO barH1  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.H1);
		BarDTO barM30 = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.M30);
		BarDTO barM15 = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.M15);
		BarDTO barM5  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.M5);
		BarDTO barM1  = metaTraderService.getLastBar(account, accountId, symbol, PeriodType.M1);

		
		StringBuilder response = new StringBuilder();
		response.append(OutcomeMsgType.isLastBarResponce.getId());
		response.append(SEPARATOR);
		response.append(symbol.name());
		response.append(SEPARATOR);
		response.append(barMN==null?"0":barMN.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barW1==null?"0":barW1.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barD1==null?"0":barD1.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barH4==null?"0":barH4.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barH1==null?"0":barH1.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barM30==null?"0":barM30.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barM15==null?"0":barM15.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barM5==null?"0":barM5.getOpenDateTime());
		response.append(SEPARATOR);
		response.append(barM1==null?"0":barM1.getOpenDateTime());
		return response.toString();
	}


	// IncomeMsgType|account|accountId|SymbolType|PeriodType|         time|O|H|L|C|V</td></tr>
	//            04| 123456|       12|    USDCHF|      1440|1316087684225|x|x|x|x|x</td></tr>
	/**
	 * {@link IncomeMsgType#isBarUpdate}
	 * */  
	private String processBarUpdate(String[] messageArray) throws IOException{
		String account    = messageArray[1].trim();
		Integer accountId = Integer.valueOf(messageArray[2].trim());
		SymbolType symbol = SymbolType.findByStr(messageArray[3].replace(".","").trim());
		PeriodType period = PeriodType.findById(messageArray[4].trim());

		
		boolean haveNext = true;
		int barValuesPos = 5;
		while(haveNext){
			BarDTO bar = new BarDTO();
			Integer openDateTime = Integer.valueOf(messageArray[barValuesPos].trim());
			// Date date = new Date(dateTime); //the number of seconds lapsed since 00:00 of the 1st of January 1970.
			bar.setOpenDateTime(openDateTime);
			bar.setOpen(  Double.valueOf(messageArray[barValuesPos+1].trim()));
			bar.setHigh(  Double.valueOf(messageArray[barValuesPos+2].trim()));
			bar.setLow(   Double.valueOf(messageArray[barValuesPos+3].trim()));
			bar.setClose( Double.valueOf(messageArray[barValuesPos+4].trim()));
			bar.setVolume(Integer.valueOf(messageArray[barValuesPos+5].trim()));
			metaTraderService.updateBar(accountId, symbol, period, bar);
			System.out.println(barValuesPos+"-"+bar.getVolume());
			barValuesPos = barValuesPos + 6;
			if(messageArray.length < barValuesPos+6)
				haveNext = false;
			
		}
		
		
		
		
		StringBuilder response = new StringBuilder();
		response.append(OutcomeMsgType.isBarUpdateResponse.getId());
		return response.toString();

		/*if(messageArray.length > statrPos+5){
			processBarUpdate(messageArray, statrPos+6 );
		}*/

	}

	/*void sendMessage(String msg){
		try{
			toClient.write(msg+"!");
			toClient.newLine();
			System.out.println("server -> client: " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}*/

}
