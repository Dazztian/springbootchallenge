package com.desafioSpring.search.controller;

import com.desafioSpring.search.DTO.ProductDTO;
import com.desafioSpring.search.DTO.searchRequestDTO;
import com.desafioSpring.search.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/articles")
public class ApiSearchRestControlller {

    @Autowired
    SearchServiceImpl searchService;


    @GetMapping()
    @ResponseBody
    public List<ProductDTO> getProducts(searchRequestDTO parametros)
    {
        return searchService.getProducts(parametros);
    }

}
