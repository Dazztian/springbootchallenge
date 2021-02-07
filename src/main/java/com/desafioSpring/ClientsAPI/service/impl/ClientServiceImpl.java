package com.desafioSpring.ClientsAPI.service.impl;

import com.desafioSpring.ClientsAPI.DAO.impl.APIClientsDAOImpl;
import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    APIClientsDAOImpl apiClientsDAO= new APIClientsDAOImpl();

    List<ClientDTO> clientList;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return apiClientsDAO.getAllClients();
    }

    @Override
    public List<ClientDTO> getAllClientsFilteredByProvince(RequestDTO requestDTO) {

        clientList = apiClientsDAO.getAllClients().stream()
                .filter( client -> client.getProvince().equalsIgnoreCase(requestDTO.getProvince().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());


        return clientList;
    }
}
