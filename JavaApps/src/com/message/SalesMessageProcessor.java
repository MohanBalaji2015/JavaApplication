package com.message;

import com.salesprocessor.Sale;
import java.io.BufferedReader;
import java.io.FileReader;


public class SalesMessageProcessor {

	public static void main(String[] args) {
        Sale sale = new Sale();
        try {
            String line;
            BufferedReader inputFile = new BufferedReader(new FileReader("input.txt"));
            while((line = inputFile.readLine()) != null) {
                sale.processNotification(line);
                sale.log.report();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}