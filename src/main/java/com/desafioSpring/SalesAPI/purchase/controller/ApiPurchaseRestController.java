package com.desafioSpring.SalesAPI.purchase.controller;

import com.desafioSpring.SalesAPI.purchase.DTO.*;
import com.desafioSpring.SalesAPI.purchase.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase-request")
public class ApiPurchaseRestController {



    @Autowired
    PurchaseServiceImpl purchaseService;


    @PostMapping
    @ResponseBody
    public ResponseDTO purchaseRequest(@RequestBody PurchaseRequestDTO request)
    {
        return purchaseService.purchaseRequest(request);
    }

    @PostMapping("/shoppingcart")
    @ResponseBody
    public ShoppingCartDTO shoppingCart(@RequestBody List<PurchaseRequestDTO> requestList)
    {
        return purchaseService.createShoppingCart(requestList);
    }
}
