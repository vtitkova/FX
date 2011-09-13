package com.dmma.fxjai.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.fxjai.core.services.PocService;

public class Connector implements Runnable{
	private static Connector INSTANCE;
	private static final Integer PORT = 1777;
	private static final Logger log = LoggerFactory.getLogger(Connector.class);
	private ServerSocket servSocket;
	private ConnectorStatus connectorStatus;
	
	private PocService pocService;
	
	public static Connector get() {
		return INSTANCE;
	}
	
	public Connector(){
		INSTANCE = this;
		connectorStatus = new ConnectorStatus();
		try {
			servSocket = new ServerSocket(PORT);
			Thread t = new Thread(INSTANCE);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){ 
		log.info("Connector Server started.");
		if(servSocket==null){
			log.error("Connector failed. servSocket is null");
			return;
		}
		while(true){
			try {
				log.info("Waiting for a connection... ");
				Socket fromClientSocket = servSocket.accept();
				SocketAddress fromAddress  = fromClientSocket.getRemoteSocketAddress();
				log.info("Corrnection request accepted from " + fromAddress);
				
				ConnectionControlTread ot = new ConnectionControlTread(pocService, fromClientSocket, connectorStatus, log);
				Thread t = new Thread(ot);
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Integer getConnectionsProcessedTotal() {
		return connectorStatus.getConnectionsProcessedTotal();
	}

	public Integer getConnectionsFailed() {
		return connectorStatus.getConnectionsFailed();
	}

	public Integer getConnectionsRejected() {
		return connectorStatus.getConnectionsRejected();
	}

	public Integer getConnectionsSucceed() {
		return connectorStatus.getConnectionsSucceed();
	}

	public Integer getConnectionsActive() {
		return connectorStatus.getConnectionsActive();
	}

	public PocService getPocService() {
		return pocService;
	}

	public void setPocService(PocService pocService) {
		this.pocService = pocService;
	}

}
