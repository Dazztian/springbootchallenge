package com.desafioSpring.purchase.DTO;

public class ResponseDTO {

    private ReceiptDTO receipt;
    private StatusCodeDTO statusCode;

    public ResponseDTO(ReceiptDTO receipt, StatusCodeDTO statusCode) {
        this.receipt = receipt;
        this.statusCode = statusCode;
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
