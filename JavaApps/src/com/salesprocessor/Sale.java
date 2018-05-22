package com.salesprocessor;

import com.salesprocessor.AdjustPrice;
import com.salesprocessor.MessageParser;
import com.salesprocessor.Product;
import com.salesprocessor.SaleLog;

public class Sale {
    public SaleLog log;
    private MessageParser messageParser;
    private AdjustPrice adjustPrice;
    private Product product;

    public Sale() {
        log = new SaleLog();
    }
    public boolean processNotification(String saleNotice) {

        this.messageParser = new MessageParser(saleNotice);

        String productType = messageParser.getProductType();

        if (productType.isEmpty()) {
            return false;
        }
        this.product = log.getProduct(productType);
        this.adjustPrice = new AdjustPrice(product);
        this.product.setProductQuantity(messageParser.getProductQuantity());
        this.product.setTotalQuantity(messageParser.getProductQuantity());
        this.product.setProductPrice(messageParser.getProductPrice());
        this.product.setAdjustmentOperator(messageParser.getOperatorType());

        setProductTotalPrice();

        log.setNormalReports(saleNotice);
        log.updateProduct(product);

        return true;
    }

    private void setProductTotalPrice() {
        double adjustedPrice;
        double productValue;

        if (!product.getAdjustmentOperator().isEmpty()) {
            adjustedPrice = adjustPrice.getAdjustedPrice();
            log.setAdjustmentReports(adjustPrice.adjustmentReport());
            product.setTotalPrice(adjustedPrice);
        } else {
            productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
            product.appendTotalPrice(productValue);
        }
    }

}