package com.JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.message.SalesMessageProcessor;

public class salesprocessor {


	String message = "Hello World";	
	SalesMessageProcessor salesMessageProcessor = new SalesMessageProcessor(message);

	   @Test
	   public void testPrintMessage() {	  
	      assertEquals(message,salesMessageProcessor.printMessage());
	   }
	}
