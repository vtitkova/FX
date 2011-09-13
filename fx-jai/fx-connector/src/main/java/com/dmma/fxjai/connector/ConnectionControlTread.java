package com.dmma.fxjai.connector;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;

import com.dmma.fxjai.connector.errors.ConnectionError;
import com.dmma.fxjai.core.entities.Poc;
import com.dmma.fxjai.core.services.PocService;

public class ConnectionControlTread implements Runnable{
	private Socket fromClientSocket;
	private ConnectorStatus connectorStatus;
	private Logger log;
	private DataOutputStream toClient;
	private DataInputStream  fromClient;
	private PocService pocService;
	
	public ConnectionControlTread(PocService pocService, Socket fromClientSocket,ConnectorStatus connectorStatus, Logger log) {
		this.fromClientSocket = fromClientSocket;
		this.connectorStatus  = connectorStatus;
		this.log              = log;
		this.pocService = pocService;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		this.connectorStatus.connectionProcessStarted();
		String accountLogin;
		
		try {
			toClient = new DataOutputStream( fromClientSocket.getOutputStream());
			toClient.flush();
			fromClient = new DataInputStream(  fromClientSocket.getInputStream());
			// any message from client starts with client account id
			accountLogin   = fromClient.readLine();	
			//String msgType = fromClient.readLine();
			 
		    /* if(IncomeMsgType.isPing.isEequals(msgType)){
				//processPingMsg(accountLogin);
			}else if(IncomeMsgType.isRegistration.isEequals(msgType)){
				//processRegistration(accountLogin);
			}else if(IncomeMsgType.isActual.isEequals("3")){*/
			processActualMsg(accountLogin);
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

	
	private void processActualMsg(String accountLogin) throws IOException, ConnectionError{
		/*if(!CoreServiceFactory.get().getAccountService().isAccountExist(accountLogin)){
			throw new ConnectionError(accountLogin,ConnectionErrorTypes.UNKNOWN_ACCOUNT_LOGIN);
		}*/
		
		String aa[] = accountLogin.split(";");
		
		Poc poc = new Poc();
		poc.setCreated(new Date());
		String symbol = aa[1]; //fromClient.readUTF();
		String bid    = aa[2]; // fromClient.readUTF();
		poc.setText(symbol+bid);
		pocService.saveOrUpdate(poc);
		
/*		CoreEvent event = new CoreEvent(EKC.ACTUAL_EVENT, this.getClass().getSimpleName());
		event.addParam(EPC.SYMBOL, symbol);
		event.addParam(EPC.BID, bid);
		CoreEventManager.get().fireEvent(event);
*/		log.info("Actual from client: " +accountLogin + "|" +symbol + "|"+bid);
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
