package com.dmma.fxjai.connectorclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectorClient{
	private Socket requestSocket;
	private DataOutputStream toServer;
	private DataInputStream  fromServer;
	
	
	public ConnectorClient() {
	}
	
	public void sendPing() {
		try {
			requestSocket = new Socket("localhost", 1777);
			toServer      = new DataOutputStream(requestSocket.getOutputStream());
			toServer.flush();
			fromServer    = new DataInputStream(  requestSocket.getInputStream());
			
			sendMessage("1234567");
			sendMessage("01");
			sendMessage("Hi from client");
			
			String msg = fromServer.readUTF();	
			System.out.println("Received:" + msg);
			msg = fromServer.readUTF();	
			System.out.println("Received:" + msg);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fromServer.close();
				toServer.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	public void sendWrong() {
		try {
			requestSocket = new Socket("localhost", 1777);
			toServer      = new DataOutputStream(requestSocket.getOutputStream());
			toServer.flush();
			fromServer    = new DataInputStream(  requestSocket.getInputStream());
			
			sendMessage("1234567");
			sendMessage("x1");
			sendMessage("Hi from client");
			
			String msg = fromServer.readUTF();	
			System.out.println("Received:" + msg);
			msg = fromServer.readUTF();	
			System.out.println("Received:" + msg);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fromServer.close();
				toServer.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	public void sendFailed() {
		try {
			requestSocket = new Socket("localhost", 1777);
			toServer      = new DataOutputStream(requestSocket.getOutputStream());
			toServer.flush();
			fromServer    = new DataInputStream(  requestSocket.getInputStream());
			
			sendMessage("1234567");
			System.out.println("I posilaem vseh travku klevatj ...");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fromServer.close();
				toServer.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	public void sendRegistration() {
		try {
			requestSocket = new Socket("localhost", 1777);
			toServer      = new DataOutputStream(requestSocket.getOutputStream());
			toServer.flush();
			fromServer    = new DataInputStream(  requestSocket.getInputStream());
			sendMessage("1234567");
			sendMessage("02");
			sendMessage("DEMO");
			sendMessage("Dmitrijss");
			sendMessage("Marcenkovs");
			sendMessage("demo.fxopen.com:4483");
			
			String msg = fromServer.readUTF();	
			System.out.println("Received:" + msg);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fromServer.close();
				toServer.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	public void sendActual() {
		try {
			requestSocket = new Socket("localhost", 1777);
			toServer      = new DataOutputStream(requestSocket.getOutputStream());
			toServer.flush();
			fromServer    = new DataInputStream(  requestSocket.getInputStream());
			sendMessage("12345678");
			sendMessage("03");
			sendMessage("EURUSD");
			sendMessage("1.3614");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				fromServer.close();
				toServer.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	
	
	
	void sendMessage(String msg){
		try{
			toServer.writeUTF(msg);
			toServer.flush();
			System.out.println("client -> server:" + msg);
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	

}
