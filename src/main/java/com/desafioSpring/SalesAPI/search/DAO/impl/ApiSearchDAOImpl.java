package com.desafioSpring.SalesAPI.search.DAO.impl;

import com.desafioSpring.SalesAPI.search.DAO.ApiSearchDAO;
import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class ApiSearchDAOImpl implements ApiSearchDAO {

    public List<ProductDTO> database;

    public ApiSearchDAOImpl()  {
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
