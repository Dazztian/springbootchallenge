package com.desafioSpring.ClientsAPI.model.utils;


import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.List;


public class WriteClientJSON {

    public static void writeClients(List<ClientDTO> clientList)
    {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("src/main/resources/clients.json"), clientList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}



