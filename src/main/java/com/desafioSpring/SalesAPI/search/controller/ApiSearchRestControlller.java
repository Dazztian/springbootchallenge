package com.desafioSpring.SalesAPI.search.controller;

import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;
import com.desafioSpring.SalesAPI.search.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Diego Azpeitia
 */
@RestController
@RequestMapping("/api/v1/articles")
public class ApiSearchRestControlller {

    @Autowired
    SearchServiceImpl searchService;


    @GetMapping()
    public List<ProductDTO> getProducts(searchRequestDTO parametros)
    {
        return searchService.getProducts(parametros);
    }

}
