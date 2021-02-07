package com.desafioSpring.ClientsAPI.DAO.impl;

import com.desafioSpring.ClientsAPI.DAO.APIClientsDAO;
import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class APIClientsDAOImpl implements APIClientsDAO {


    public List<ClientDTO> database;

    public APIClientsDAOImpl()  {
        database = this.loadDatabase();
    }


    @Override
    public List<ClientDTO> getAllClients() {
        return this.database;
    }


    public List<ClientDTO>  loadDatabase()  {
        File file= null;
        try {
            file = ResourceUtils.getFile("src/main/resources/clients.json");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ClientDTO>> typeRef = new TypeReference<>() {};
        List<ClientDTO> clientDTOS = null;
        try {
            clientDTOS = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientDTOS;
    }
}
