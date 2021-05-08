package com.nisyst.ecommerce.model;

public class Orders {
    private String productCode;
    private String userID;
    private int totalQty;
    private String totalPrice;
    private int status;

    public Orders() {
    }

    public Orders(String productCode, String userID, int totalQty, String totalPrice, int status) {
        this.productCode = productCode;
        this.userID = userID;
        this.totalQty = totalQty;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
