package com.dmma.fxjai.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;

import com.dmma.fxjai.connector.errors.ConnectionError;
import com.dmma.fxjai.connector.types.IncomeMsgType;
import com.dmma.fxjai.core.services.MetaTraderService;
import com.dmma.fxjai.core.types.SymbolType;

public class ConnectionProcessor implements Runnable{
	private Socket fromClientSocket;
	private ConnectorStatus connectorStatus;
	private Logger log;
	private DataOutputStream toClient;
	private DataInputStream  fromClient;
	private MetaTraderService metaTraderService;
	
	public ConnectionProcessor(MetaTraderService metaTraderService, Socket fromClientSocket,ConnectorStatus connectorStatus, Logger log) {
		this.fromClientSocket = fromClientSocket;
		this.connectorStatus  = connectorStatus;
		this.log              = log;
		this.metaTraderService = metaTraderService;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		this.connectorStatus.connectionProcessStarted();
		String msgFromClient;
		
		try {
			toClient = new DataOutputStream(fromClientSocket.getOutputStream());
			toClient.flush();
			fromClient = new DataInputStream(fromClientSocket.getInputStream());
			
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
			}else if(IncomeMsgType.isActual.isEequals("3")){
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
		sendMessage(OutcomeMsgType.isPong.toString());
		sendMessage("Hello client "+accountLogin+", you sent me '"+msg+"'");
	}

	
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
			toClient.writeUTF(msg);
			toClient.flush();
			System.out.println("server -> client" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

}
