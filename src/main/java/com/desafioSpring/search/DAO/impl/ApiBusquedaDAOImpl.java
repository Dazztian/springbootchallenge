package com.desafioSpring.search.DAO.impl;

import com.desafioSpring.search.DAO.ApiBusquedaDAO;
import com.desafioSpring.search.DTO.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class ApiBusquedaDAOImpl implements ApiBusquedaDAO {

    public List<ProductDTO> database;

    public ApiBusquedaDAOImpl()  {
        database = this.loadDatabase();
    }

    @Override
    public List<ProductDTO> getAllProducts() { return this.database; }


    public List<ProductDTO>  loadDatabase()  {
        File file= null;
        try {
            file = ResourceUtils.getFile("src/main/resources/products.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductDTO>> typeRef = new TypeReference<>() {};
        List<ProductDTO> ProductDTOS = null;
        try {
            ProductDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ProductDTOS;
    }

}
