package com.desafioSpring.purchase.service;

import com.desafioSpring.purchase.DTO.ShoppingCartDTO;
import com.desafioSpring.purchase.DTO.PurchaseRequestDTO;
import com.desafioSpring.purchase.DTO.ResponseDTO;

import java.util.List;

public interface PurchaseService {

    public ResponseDTO purchaseRequest(PurchaseRequestDTO request);
    public ShoppingCartDTO createShoppingCart(List<PurchaseRequestDTO> requestList);
}
