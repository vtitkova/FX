package com.dmma.fxjai.connectorclient;


public class ConnectorClientMain{
		
	public static void main(String[] args) {
		ConnectorClient client = new ConnectorClient();
		//client.sendPing();
		//client.sendWrong();
		//client.sendFailed();
		client.sendActual();
		//client.sendRegistration();
	}
}
