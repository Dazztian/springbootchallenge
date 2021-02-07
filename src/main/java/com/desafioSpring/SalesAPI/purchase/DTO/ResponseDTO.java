package com.desafioSpring.SalesAPI.purchase.DTO;

public class ResponseDTO {

    private ReceiptDTO receipt;
    private StatusCodeDTO statusCode;
    private Integer totalPrice;

    public ResponseDTO(ReceiptDTO receipt, StatusCodeDTO statusCode, Integer totalPrice) {
        this.receipt = receipt;
        this.statusCode = statusCode;
        this.totalPrice = totalPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ReceiptDTO getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptDTO receipt) {
        this.receipt = receipt;
    }

    public StatusCodeDTO getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCodeDTO statusCode) {
        this.statusCode = statusCode;
    }
}
