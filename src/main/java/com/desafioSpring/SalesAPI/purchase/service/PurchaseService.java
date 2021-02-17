package com.desafioSpring.SalesAPI.purchase.service;

import com.desafioSpring.SalesAPI.purchase.DTO.ShoppingCartDTO;
import com.desafioSpring.SalesAPI.purchase.DTO.PurchaseRequestDTO;
import com.desafioSpring.SalesAPI.purchase.DTO.ResponseDTO;

import java.util.List;


/**
 * @author Diego Azpeitia
 */
public interface PurchaseService {

    public ResponseDTO purchaseRequest(PurchaseRequestDTO request);
    public ShoppingCartDTO createShoppingCart(List<PurchaseRequestDTO> requestList);
}
