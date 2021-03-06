package com.desafioSpring.SalesAPI.purchase.service.impl;

import com.desafioSpring.SalesAPI.purchase.DTO.*;
import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;
import com.desafioSpring.SalesAPI.purchase.service.PurchaseService;
import com.desafioSpring.SalesAPI.search.service.impl.SearchServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    searchRequestDTO searchRequestDTO =  new searchRequestDTO(null, null, null, null,null, null, null, null,null, null, null, null, null);

    SearchServiceImpl searchService= new SearchServiceImpl();

    //La key es la id del product, y el value la cant pedida
    HashMap<Integer, Integer> amountRequestedByProductId = new HashMap<>();



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

            if (  article.getQuantity() + obtainAmountAlreadyRequested(article) > productDTO.getQuantity() )
            {
                return createResponseDTO(responseArticleDTOList, 0, 400, "ERROR, WRONG QUANTITY", "No se pudo completar la solicitud,  uno de los productos no tiene la cantidad suficiente en stock");
            }

            totalPrice+=productDTO.getPrice() * productDTO.getQuantity();

            //En cada purchase request voy acumulando la cantidad pedida asociando el id con las cantidades
            //Para asi reflejar la reducción  del stock
            updateAmountRequested(article, productDTO);

        }
        ResponseDTO responseDTO = createResponseDTO(responseArticleDTOList, totalPrice,200, "OK", "La solicitud se completo con exito");

        return responseDTO;

    }


    //Se encarga de actualizar el hashmap con la información de los pedidos
    private void updateAmountRequested(ArticleDTO article, ProductDTO productDTO) {


        if (amountRequestedByProductId.containsKey(article.getProductId()) )
        {
            int nuevaCantidad = obtainAmountAlreadyRequested(article) + article.getQuantity();

            amountRequestedByProductId.put(article.getProductId(), nuevaCantidad);
        }
        else {
            amountRequestedByProductId.put(article.getProductId(), article.getQuantity());
        }

    }

    //Si ya hay una solicitud, entonces devuelve el valor que contiene, sino devuelve 0.
    private Integer obtainAmountAlreadyRequested(ArticleDTO article) {
        if(amountRequestedByProductId.containsKey(article.getProductId()))
        {
            return amountRequestedByProductId.get(article.getProductId());
        }
        else
        {
            return 0;
        }

    }

    //Me gustaria implementar una estructura que contabilice la cantidad pedidas de las distintas request
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
        //int receiptId = (new Random()).nextInt(100000) ;
        int receiptId = 100000 ;
        ReceiptDTO responseReceiptDTO = new ReceiptDTO(receiptId, status, responseArticleDTOList);
        StatusCodeDTO responseStatusCodeDTO = new StatusCodeDTO(statusCode, message);
        return new ResponseDTO(responseReceiptDTO, responseStatusCodeDTO, totalPrice);
    }

}
