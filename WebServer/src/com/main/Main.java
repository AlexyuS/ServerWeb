package com.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import com.app.classloader.EndpointsPoolService;
import com.thread.ClientHandlingThread;


public class Main {
	static int PORT =8082;
	public static void main(String args[]) {
		
		EndpointsPoolService.getInstance().getEndpoints();
		try (ServerSocket serverConnect = new ServerSocket(PORT)){	
			System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
	
			while (true) {
				Socket socket = serverConnect.accept();
				Thread t =  new Thread(new ClientHandlingThread(socket));
				t.start();
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}
}


