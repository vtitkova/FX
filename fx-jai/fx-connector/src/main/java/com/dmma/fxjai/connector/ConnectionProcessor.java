package com.dmma.fxjai.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;

import com.dmma.fxjai.connector.errors.ConnectionError;
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
			
			//String msgType = fromClient.readLine();
			 
		    /* if(IncomeMsgType.isPing.isEequals(msgType)){
				//processPingMsg(accountLogin);
			}else if(IncomeMsgType.isRegistration.isEequals(msgType)){
				//processRegistration(accountLogin);
			}else if(IncomeMsgType.isActual.isEequals("3")){*/
			processActualMsg(msgFromClient);
			/*}else{
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

	/*private void processPingMsg(String accountLogin) throws IOException {
		String msg = fromClient.readLine();
		log.info("Ping from client: " +accountLogin + "|" +IncomeMsgType.isPing + "|"+msg);
		//01|Hello client xxxxx, you sent me 'test text sent to server'
		sendMessage(OutcomeMsgType.isPong.toString());
		sendMessage("Hello client "+accountLogin+", you sent me '"+msg+"'");
	}*/

	
	private void processActualMsg(String msgFromClient) throws IOException, ConnectionError{
		/*if(!CoreServiceFactory.get().getAccountService().isAccountExist(accountLogin)){
			throw new ConnectionError(accountLogin,ConnectionErrorTypes.UNKNOWN_ACCOUNT_LOGIN);
		}*/
		
		String aa[] = msgFromClient.split(";");
		// account|msgType|SYMBOL|BID   |DATE         
		String account   = aa[0];
		String msgType   = aa[1];
		String symbolStr = aa[2];
		String bidStr    = aa[3];
		String dateLongString = aa[4];
		
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
