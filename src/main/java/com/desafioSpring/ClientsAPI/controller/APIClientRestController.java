package com.desafioSpring.ClientsAPI.controller;


import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.DTO.ResponseDTO;
import com.desafioSpring.ClientsAPI.service.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Diego Azpeitia
 */
@RestController
@RequestMapping("/api/clients")
public class APIClientRestController {

    ClientServiceImpl clientService = new ClientServiceImpl();


    /*Método para crear un cliente, recibe como argumento un cliente y devuelve un ResponseDTO
    que contiene información sobre la operación si fue o no exitosa
    */
    @PostMapping("/createclient")
    @ResponseBody
    public ResponseDTO createClient(@RequestBody ClientDTO clientDTO)
    {
        return clientService.createClient(clientDTO);
    }

    /* Devuelve la lista de todos los clientes*/
    @GetMapping("/getclients")
    @ResponseBody
    public List<ClientDTO> getAllClients()
    {
        return clientService.getAllClients();
    }

    /* Trae la lista de clientes filtrados por una provincia particular */
    @GetMapping("/getclientsfiltered")
    @ResponseBody
    public List<ClientDTO> getAllClientsFilteredByProvince(@RequestBody RequestDTO province)
    {
        return clientService.getAllClientsFilteredByProvince(province);
    }
}
