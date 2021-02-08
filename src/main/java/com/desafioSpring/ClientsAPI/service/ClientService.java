package com.desafioSpring.ClientsAPI.service;

import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.DTO.ResponseDTO;

import java.util.List;

public interface ClientService {

    //Create Client podría devolver una responseDto con los clientes del sistema + un msj que diga que la operacion salió ok
    public ResponseDTO createClient(ClientDTO clientDTO);
    public List<ClientDTO> getAllClients();
    public List<ClientDTO> getAllClientsFilteredByProvince(RequestDTO requestDTO);
    public boolean clientAlreadyExists(ClientDTO clientDTO);
    public boolean clientIdAlreadyExists(ClientDTO clientDTO);

}
