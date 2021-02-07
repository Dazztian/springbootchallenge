package com.desafioSpring.SalesAPI.search.DAO;

import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;

import java.util.List;

public interface ApiSearchDAO {

    public List<ProductDTO> getAllProducts();

}
