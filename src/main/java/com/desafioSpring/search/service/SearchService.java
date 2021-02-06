package com.desafioSpring.search.service;

import com.desafioSpring.search.DTO.ProductDTO;
import com.desafioSpring.search.DTO.searchRequestDTO;

import java.util.List;

public interface SearchService {

        public List<ProductDTO> getProducts(searchRequestDTO parametros) ;
        public List<ProductDTO> getProductsFiltered(searchRequestDTO parametros);

}
