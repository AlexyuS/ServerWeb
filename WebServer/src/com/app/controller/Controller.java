package com.app.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Controller {
	
	public String doSomething1() {
			return "Resources/test.html";	
	}
	
	public PrintWriter doSomething2(InputStream stream) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new String("DoSomething2"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return writer;
	}
	
	
}
