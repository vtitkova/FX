package com.dmma.fxjai.connectorclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ConnectorClientMain{
		
	public static void main(String[] args) {
		//D1( 1440,   "D1"),
		//W1(10080,   "W1"),
		//MN1(43200, "MN1");
		
		//String msgToSend = "1;hi";     //              1316 603 432 624
		//String msgToSend = "4;293165;1;EURUSD.;43200;978307200;1.1;0.9599;0.9113;0.9379;4930"; // update MN1 978307200
		String msgToSend = "4;293165;1;EURUSD.;10080;978307200;1.1;0.9599;0.9113;0.9379;4930"; // update W1 978307200
		
		//String msgToSend = "03;293165;1;EURUSD.";
		
		
		/*	msgToSend+=";978307201;0.9421;0.9599;0.9113;0.9379;4931";
		msgToSend+=";978307202;0.9421;0.9599;0.9113;0.9379;4932";
		msgToSend+=";978307203;0.9421;0.9599;0.9113;0.9379;4933";
		msgToSend+=";978307204;0.9421;0.9599;0.9113;0.9379;4934";
		msgToSend+=";978307205;0.9421;0.9599;0.9113;0.9379;4935";*/
		
		Socket requestSocket = null;
		BufferedWriter  toClient = null;
		BufferedReader  fromClient = null;
		
		try {
			requestSocket = new Socket("localhost", 1777);
			toClient   = new BufferedWriter(new OutputStreamWriter(requestSocket.getOutputStream()));
			fromClient = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
	
			toClient.write(msgToSend);
			toClient.newLine();
			toClient.flush();

			System.out.println("clienc->server: " + msgToSend);
			
			String msgFromClient   = fromClient.readLine();	

			System.out.println("server response: " + msgFromClient);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				toClient.close();
				fromClient.close();
				requestSocket.close();
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
		
		
		
	}
}
