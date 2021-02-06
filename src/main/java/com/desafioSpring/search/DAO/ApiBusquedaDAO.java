package com.desafioSpring.search.DAO;

import com.desafioSpring.search.DTO.ProductDTO;

import java.util.List;

public interface ApiBusquedaDAO {

    public List<ProductDTO> getAllProducts();

}
