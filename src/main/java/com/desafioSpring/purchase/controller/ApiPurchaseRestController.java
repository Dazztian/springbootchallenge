package com.desafioSpring.purchase.controller;

import com.desafioSpring.purchase.DTO.*;
import com.desafioSpring.purchase.service.impl.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchase-request")
public class ApiPurchaseRestController {



    @Autowired
    PurchaseServiceImpl purchaseService;


    @PostMapping
    @ResponseBody
    public ResponseDTO purchaseRequest(@RequestBody purchaseRequestDTO request)
    {
        return purchaseService.purchaseRequest(request);
    }
}
