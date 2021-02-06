package com.desafioSpring.purchase.controller;

import com.desafioSpring.purchase.DTO.*;
import com.desafioSpring.purchase.service.impl.PurchaseServiceImpl;
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
    public ResponseDTO purchaseRequest(@RequestBody purchaseRequestDTO request)
    {
        //La request llega 10 pts
        System.out.println("PROBANDO CODIGO");
        System.out.println(request.getUsername());
        for(ArticleDTO articleDTO: request.getArticles())
        {
            System.out.println(articleDTO.getProductId());
            System.out.println(articleDTO.getDiscount());
            System.out.println(articleDTO.getQuantity());

        }

        return purchaseService.purchaseRequest(request);
        //return new ResponseDTO(7);
    }
}
