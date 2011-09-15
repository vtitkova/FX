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
import com.dmma.fxjai.core.services.MetaTraderService;
import com.dmma.fxjai.core.types.SymbolType;

public class ConnectionProcessor implements Runnable{
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
		String msgFromClient;
		
		try {
			// toClient = new DataOutputStream(fromClientSocket.getOutputStream());
			// toClient.flush();
			// fromClient = new DataInputStream(fromClientSocket.getInputStream());
			
			toClient   = new BufferedWriter(new OutputStreamWriter(fromClientSocket.getOutputStream()));
			fromClient = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

			 
			// any message from client starts with client account id
			msgFromClient   = fromClient.readLine();	
			
			String messageArray[] = msgFromClient.split(";");
			String msgType   = messageArray[1];
			Integer msgId = Integer.valueOf(msgType);
			
			//String msgType = fromClient.readLine();
			 
		    if(IncomeMsgType.isPing.isEequals(msgId)){
				processPingMsg(messageArray);
			}else if(IncomeMsgType.isRegistration.isEequals(msgId)){
				//processRegistration(accountLogin);
			}else if(IncomeMsgType.isActual.isEequals("3")){  //TODO wtf
				processActualMsg(messageArray);
			}
			/*else{
				this.connectorStatus.connectionProcessRejected();
				log.warn("Connection rejected - Unknown type:" +msgType);
				sendMessage("Connection rejected - Unknown type:" +msgType);
				return;
			}*/
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

	
	/*private void processRegistration(String accountLogin) throws IOException, ConnectionError {
		String accountType = fromClient.readUTF();
		AccountType type = AccountType.findByName(accountType);
		if(accountType==null){
			throw new ConnectionError(accountLogin, ConnectionErrorTypes.UNKNOWN_ACCOUNT_TYPE);
		}
		String name = fromClient.readUTF();
		String surname = fromClient.readUTF();
		String serverUrl = fromClient.readUTF();
			
		Boolean retVal = CoreServiceFactory.get().getAccountService().registerOrUpdateAccount(accountLogin, type, name, surname, serverUrl);
		sendMessage(OutcomeMsgType.isRegistrationStatus.toString());
		sendMessage(retVal.toString());
	}
*/
	// account|msgType|free text 
	private void processPingMsg(String[] messageArray) throws IOException {
		String account  = messageArray[0];
		String freeText = messageArray[2];

		log.info("Ping from client: " +account + "|" +IncomeMsgType.isPing + "|"+freeText);
		
		String out = OutcomeMsgType.isPong.getId()+";";
		out+="Hello client "+account+", you sent me <"+freeText+">";
		//out = "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
		//out += "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
		//out += "1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000";
		
		sendMessage(out);
		sendMessage("hehe");
	}

//6666777777777788888888889999999999000000000055555566666666667777777777888888888899999999990000000000111111111122222222223333333333444444444455555555556666666666777777777788888888889999999999000000000011111111112222222222333333333344444444445555555555666666ллллллллP⌡nлллллллл
	
	// account|msgType|SYMBOL|BID   |DATE         
	private void processActualMsg(String[] messageArray) throws IOException, ConnectionError{
		String account   = messageArray[0];
		String symbolStr = messageArray[2];
		String bidStr    = messageArray[3];
		String dateLongString = messageArray[4];
		
		SymbolType symbol = SymbolType.findByStr(symbolStr.replace(".",""));
		Double bid =Double.valueOf(bidStr);
		Date date = new Date(Long.valueOf(dateLongString));
		
		metaTraderService.setActual(account, symbol, bid, date);
		
	}
	
	
	
	void sendMessage(String msg){
		try{
			toClient.write(msg);
			toClient.newLine();
			System.out.println("server -> client: " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

}
