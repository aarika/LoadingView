package com.nisyst.ecommerce.model;

public class Inventory {

    private String productCode;
    private String productName;
    private int totalQty;
    private int soldQty;
    private int remainingQty;

    public Inventory() {
    }

    public Inventory(String productCode, String productName, int totalQty, int soldQty, int remainingQty) {
        this.productCode = productCode;
        this.productName = productName;
        this.totalQty = totalQty;
        this.soldQty = soldQty;
        this.remainingQty = remainingQty;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public int getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(int remainingQty) {
        this.remainingQty = remainingQty;
    }
}
