package com.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import com.app.classloader.EndpointsPoolService;
import com.app.exception.client.ClientException;
import com.app.exception.server.NotImplementedServerException;
import com.app.exception.server.ServerException;
import com.app.utils.HeaderMessage;
import com.app.utils.HttpResponse;
import com.app.utils.HttpResponse.HttpResponseBuilder;
import com.app.utils.constants.ServerStatusCode;
import com.app.utils.constants.StatusCode;
import com.app.utils.constants.SuccessStatusCode;
import com.app.utils.messages.ServerErrorMessages;

public class ClientHandlingThread implements Runnable {
	private final Socket socket;
	private Logger log = Logger.getLogger(ClientHandlingThread.class.getName());

	public ClientHandlingThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		log.info("Client has been connected");
		try {
			HeaderMessage stringList = getHeaderFromMessage();

			String endpoint = ClientHandlingUtils.determineEndpoint(stringList);

			Map<String, Method> endpoints = EndpointsPoolService.getInstance().getEndpoints();

			Method methodToInvoke = endpoints.get(endpoint);
			if(methodToInvoke==null) {
				throw new NotImplementedServerException();
			}
			boolean isReturnTypeOfString = ClientHandlingUtils.hasStringReturnType(methodToInvoke);
			if (!isReturnTypeOfString) {
				throw new NotImplementedServerException();
			}

			Object clazz = methodToInvoke.getDeclaringClass().newInstance();
			String response = (String) methodToInvoke.invoke(clazz);

			String body = ClientHandlingUtils.getBodyFromPath(response);

			sendResponse(socket, body, SuccessStatusCode.SUCCESS);
			
		} catch (ServerException e) {
			sendResponse(socket, "Server error:" + e.getMessage(), e.getError());
		} catch (ClientException e) {
			sendResponse(socket, "Client error:" + e.getMessage(), e.getError());
		} catch (Exception e) {
			sendResponse(socket, "Server error:" + ServerErrorMessages.E_500, ServerStatusCode.INTERNAL_ERROR);
		}

	}

	private HeaderMessage getHeaderFromMessage() throws IOException {
		InputStreamReader rd = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(rd);
		HeaderMessage stringList = new HeaderMessage();
		String input;
		do {
			input = reader.readLine();
			stringList.add(input);
		} while (!ClientHandlingUtils.isNullOrEmpty(input));
		return stringList;
	}

	public void sendResponse(Socket socket, String body, StatusCode statusCode) {
		try {
			HttpResponseBuilder responseBuilder = new HttpResponse().getBuilder();
			responseBuilder.setBody(body);

			HttpResponse responseMsg = responseBuilder.build(statusCode);

			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(responseMsg.printHeader().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeSocket(socket);
		}

	}

	public void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
