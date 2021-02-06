package com.desafioSpring.purchase.service.impl;

import com.desafioSpring.purchase.DTO.ArticleDTO;
import com.desafioSpring.purchase.DTO.purchaseRequestDTO;
import com.desafioSpring.search.DTO.ProductDTO;
import com.desafioSpring.search.DTO.searchRequestDTO;
import com.desafioSpring.purchase.DTO.ResponseDTO;
import com.desafioSpring.purchase.service.PurchaseService;
import com.desafioSpring.search.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    SearchServiceImpl searchService= new SearchServiceImpl();


    @Override
    public ResponseDTO purchaseRequest(purchaseRequestDTO request)
    {

        System.out.println("COMPRANDO EL PRODUCTO");
        for(ArticleDTO article: request.getArticles())
        buyProduct(article.getProductId());
        return null;
    }



    //Primero lo hago para 1 elemento, luego lo desarrollo para toda una lista
    //public ResponseDTO buyProduct(Integer id)
    public ProductDTO buyProduct(Integer id)
    {
        /*System.out.println("YENDO A BUSCAR EL PRODUCTO");
        searchRequestDTO request = new searchRequestDTO();
        System.out.println(Optional.of(id).get());
        request.setId(Optional.of(id));
        System.out.println(request.getId());

        searchService.getProducts(request);

        System.out.println("ENCONTRE EL PRODUCTO");

        System.out.println("IMPRIMIENDO EL RESULTADO");
        for (ProductDTO productDTO: searchService.getProducts(request))
        System.out.println(productDTO.getName());*/
        return null;
    }
}
