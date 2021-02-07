package com.desafioSpring.SalesAPI.search.service;

import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;

import java.util.List;

public interface SearchService {

        public List<ProductDTO> getProducts(searchRequestDTO parametros) ;
        public List<ProductDTO> getProductsFiltered(searchRequestDTO parametros);

}
