package com.desafioSpring.purchase.service;

import com.desafioSpring.purchase.DTO.purchaseRequestDTO;
import com.desafioSpring.purchase.DTO.ResponseDTO;

public interface PurchaseService {

    public ResponseDTO purchaseRequest(purchaseRequestDTO request);
}
