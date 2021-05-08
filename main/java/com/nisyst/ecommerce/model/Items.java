package com.nisyst.ecommerce.model;

public class Items {
    private String productCode;
    private String productName;
    private int productQty;
    private int productPrice;
    private int totalQty;
    private int soldQty;
    private int remainingQty;

    public Items() {
    }

    public Items(String productCode, String productName, int productQty, int productPrice, int totalQty, int soldQty, int remainingQty) {
        this.productCode = productCode;
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
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

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
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

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
