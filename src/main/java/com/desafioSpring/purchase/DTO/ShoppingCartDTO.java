package com.desafioSpring.purchase.DTO;

import java.util.List;

public class ShoppingCartDTO {

    private List<ResponseDTO> responseList;
    private Integer totalPrice;


    public ShoppingCartDTO(List<ResponseDTO> responseList, Integer totalPrice) {
        this.responseList = responseList;
        this.totalPrice = totalPrice;
    }

    public List<ResponseDTO> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<ResponseDTO> responseList) {
        this.responseList = responseList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
