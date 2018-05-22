package com.salesprocessor;

import com.salesprocessor.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class SaleLog {
    private HashMap<String, Product> lineItems = new HashMap<>();
    private double totalSalesValue;
    @SuppressWarnings("rawtypes")
	private ArrayList normalReports;
    @SuppressWarnings("rawtypes")
	private ArrayList adjustmentReports;

    public SaleLog() {
        this.normalReports = new ArrayList();
        this.adjustmentReports = new ArrayList();
        this.totalSalesValue = 0.0;
    }

    public Product getProduct(String type) {
        return lineItems.getOrDefault(type,new Product(type));
    }

    public void updateProduct(Product product){lineItems.put(product.getProductType(), product);}

    public ArrayList getNormalReports() {
        return normalReports;
    }

    public void setNormalReports(String normalReport) {
        this.normalReports.add(normalReport);
    }

    public ArrayList getAdjustmentReports() {return adjustmentReports;}

    public void setAdjustmentReports(String adjustmentReport) {this.adjustmentReports.add(adjustmentReport);}

    public double getTotalSalesValue() {
        return totalSalesValue;
    }

    public void appendTotalSalesValue(double productTotalPrice) { totalSalesValue += productTotalPrice;}

    public void setTotalSalesValue(double productTotalPrice) { totalSalesValue = productTotalPrice;}

    public void report() {

        if((normalReports.size() % 10) == 0 && normalReports.size() !=0) {
            setTotalSalesValue(0.0);
            System.out.println("10 sales appended to log");
            System.out.println("-------------------------------------------");
            System.out.println("|Product           |Quantity   |Value      |");
            System.out.println("-------------------------------------------");
            lineItems.forEach((k,v) -> formatReports(k,v));
            System.out.println("-------------------------------------------");
            System.out.println(String.format("|%-30s|%-11.2f|","Total Sales",getTotalSalesValue()));
            System.out.println("-------------------------------------------");
            System.out.println("End\n\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if((normalReports.size() % 50) == 0 && normalReports.size() !=0) {
            System.out.println("Application reached 50 messages and cannot process further. The following are the adjustment records made;\n");

            getAdjustmentReports().forEach(System.out::println);
            System.exit(1);
        }
    }

    public void formatReports(String type, Product product) {
        String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(), product.getTotalPrice());
        appendTotalSalesValue(product.getTotalPrice());
        System.out.println(lineItem);
    }


}
