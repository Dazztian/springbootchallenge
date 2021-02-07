package com.desafioSpring.ClientsAPI.service;

import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;

import java.util.List;

public interface ClientService {

    public ClientDTO createClient(ClientDTO clientDTO);
    public List<ClientDTO> getAllClients();
    public List<ClientDTO> getAllClientsFilteredByProvince(RequestDTO requestDTO);

}
