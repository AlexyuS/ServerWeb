package com.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import com.app.exception.client.BadRequestExeption;
import com.app.exception.client.ClientException;
import com.app.exception.client.MethodNotAllowedException;
import com.app.exception.client.NotFoundException;
import com.app.exception.server.InternalServerErrorException;

public class ClientHandlingUtils {

	public static String getBodyFromPath(String path) throws Exception {
		try (FileReader in = new FileReader(new File(path)); BufferedReader fileReader = new BufferedReader(in)) {
			return fileReader.lines().collect(Collectors.joining("\n"));
		} catch (FileNotFoundException e) {
			throw new NotFoundException(getBodyFromPath("Resources/errors/notFound.html"));
		} catch (Exception e) {
			throw new InternalServerErrorException() ;
		}
	}

	public static String determineEndpoint(List<String> headerLines) throws ClientException {

		if (headerLines.size() == 0) {
			throw new BadRequestExeption();
		}

		String method = headerLines.get(0).split(" ")[0];
		if (!method.equals("GET")) {
			throw new MethodNotAllowedException();
		}

		String endpoint = headerLines.get(0).split(" ")[1];
		return endpoint.substring(1, endpoint.length());

	}

	public static boolean endpointMatching(String methodName, String endpoint) {

		if (methodName.equals(endpoint)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasStringReturnType(List<Method> methodToCall) {
		return methodToCall.get(0).getReturnType().getName().equals(String.class.getName());
	}

	public static boolean isNullOrEmpty(String value) {
		String EMPTY_VALUE = "";
		if (value == null) {
			return true;
		}

		if (value.equals(EMPTY_VALUE)) {
			return true;
		}

		return false;
	}
}
