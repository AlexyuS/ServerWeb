package com.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.app.controller.Controller;
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

	Logger LOGGER = Logger.getLogger(ClientHandlingThread.class.getName());

	public ClientHandlingThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			InputStreamReader rd = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(rd);
			HeaderMessage stringList = new HeaderMessage();
			String input;
			do {
				input = reader.readLine();
				stringList.add(input);
			} while (!ClientHandlingUtils.isNullOrEmpty(input));

			String endpoint = ClientHandlingUtils.determineEndpoint(stringList);

			List<Method> methods = Arrays.asList(Controller.class.getMethods());
			List<Method> methodsToCall = methods.stream()
					.filter(e -> ClientHandlingUtils.endpointMatching(e.getName(), endpoint))
					.collect(Collectors.toList());

			if (methodsToCall.size() > 1) {
				throw new NotImplementedServerException();

			}
			if (methodsToCall.size() == 0) {
				throw new NotImplementedServerException();
			}

			boolean isReturnTypeOfString = ClientHandlingUtils.hasStringReturnType(methodsToCall);
			if (!isReturnTypeOfString) {
				throw new NotImplementedServerException();
			}

			String response = (String) methodsToCall.get(0).invoke(Controller.class.newInstance());

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
