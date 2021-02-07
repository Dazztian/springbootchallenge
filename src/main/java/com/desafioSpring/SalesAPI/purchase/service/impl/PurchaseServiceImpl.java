package com.desafioSpring.SalesAPI.purchase.service.impl;

import com.desafioSpring.SalesAPI.purchase.DTO.*;
import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;
import com.desafioSpring.SalesAPI.purchase.service.PurchaseService;
import com.desafioSpring.SalesAPI.search.service.impl.SearchServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    searchRequestDTO searchRequestDTO =  new searchRequestDTO(null, null, null, null,null, null, null, null,null, null, null, null, null);

    SearchServiceImpl searchService= new SearchServiceImpl();


    //Tomo como approach que se puedan procesar TODOS los productos o ninguno
    //con que solo 1 "article" no se puede satisfacer, la solicitud es invalida
    @Override
    public ResponseDTO purchaseRequest(PurchaseRequestDTO request)
    {

        List<ArticleDTO> responseArticleDTOList= request.getArticles();
        Integer totalPrice =0;


        for(ArticleDTO article: request.getArticles())
        {
            ProductDTO productDTO=buyProduct(article.getProductId());
            //Con que solo 1 producto falle, ya tiro abajo toda la operacion
            if(productDTO == null)
            {
                //Armamos la response fallida
                return createResponseDTO(responseArticleDTOList,0, 404, "ERROR, NOT FOUND", "No se pudo completar la solicitud, no se encontró uno de los productos");
            }
            if (  article.getQuantity() > productDTO.getQuantity() )
            {
                return createResponseDTO(responseArticleDTOList, 0, 400, "ERROR, WRONG QUANTITY", "No se pudo completar la solicitud,  uno de los productos no tiene la cantidad suficiente en stock");
            }

            totalPrice+=productDTO.getPrice() * productDTO.getQuantity();
        }
        ResponseDTO responseDTO = createResponseDTO(responseArticleDTOList, totalPrice,200, "OK", "La solicitud se completo con exito");

        return responseDTO;

    }

    @Override
    public ShoppingCartDTO createShoppingCart(List<PurchaseRequestDTO> requestList)
    {
        List<ResponseDTO> reponseList = new ArrayList<>();
        Integer totalPrice=0;

        for(PurchaseRequestDTO purchaseRequestDTO: requestList)
        {
            ResponseDTO responseDTO= purchaseRequest(purchaseRequestDTO);
            reponseList.add(responseDTO);
            totalPrice+=responseDTO.getTotalPrice();
        }

        return new ShoppingCartDTO(reponseList, totalPrice);
    }


    //Compro de a 1 producto
    public ProductDTO buyProduct(Integer id)
    {
        searchRequestDTO request = searchRequestDTO.createSearchRequestDTOOnlyWithId(id);

        //CHECKEAMOS QUE NO DEVUELVA LISTA VACIA
        if(searchService.getProducts(request).size() < 1)
            return null;
        else
        {
            ProductDTO productDTO = searchService.getProducts(request).get(0);
            return productDTO;
        }

    }

    private ResponseDTO createResponseDTO(List<ArticleDTO> responseArticleDTOList, int totalPrice, int statusCode, String status, String message) {
        //De manera arbitraria el id del response es aleatorio
        int receiptId = (new Random()).nextInt(100000) ;
        ReceiptDTO responseReceiptDTO = new ReceiptDTO(receiptId, status, responseArticleDTOList);
        StatusCodeDTO responseStatusCodeDTO = new StatusCodeDTO(statusCode, message);
        return new ResponseDTO(responseReceiptDTO, responseStatusCodeDTO, totalPrice);
    }

}
