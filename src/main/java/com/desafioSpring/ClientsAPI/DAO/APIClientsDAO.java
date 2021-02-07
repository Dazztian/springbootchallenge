package com.desafioSpring.ClientsAPI.DAO;

import com.desafioSpring.ClientsAPI.DTO.ClientDTO;

import java.util.List;

public interface APIClientsDAO {

    public List<ClientDTO> getAllClients();
}
