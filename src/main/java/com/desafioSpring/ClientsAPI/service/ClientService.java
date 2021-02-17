package com.desafioSpring.ClientsAPI.service;

import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.DTO.ResponseDTO;

import java.util.List;

/**
 * @author Diego Azpeitia
 */

public interface ClientService {

    /* Método que recibe un cliente y devuelve un ResponseDTO que contiene */
    public ResponseDTO createClient(ClientDTO clientDTO);
    /* Devuelve la lista de TODOS los clientes */
    public List<ClientDTO> getAllClients();
    /* Devuelve todos los clientes que pertenezcan a una misma provincia*/
    public List<ClientDTO> getAllClientsFilteredByProvince(RequestDTO requestDTO);
    /* Devuelve true/false dependiendo si el cliente ya existe en el sistema*/
    public boolean clientAlreadyExists(ClientDTO clientDTO);
    /* Devuelve true/false dependiendo si el ID del cliente ya existe en el sistema*/
    public boolean clientIdAlreadyExists(ClientDTO clientDTO);

}
