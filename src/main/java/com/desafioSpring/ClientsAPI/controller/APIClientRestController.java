package com.desafioSpring.ClientsAPI.controller;


import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.DTO.ResponseDTO;
import com.desafioSpring.ClientsAPI.service.impl.ClientServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class APIClientRestController {

    ClientServiceImpl clientService = new ClientServiceImpl();


    @PostMapping("/createclient")
    @ResponseBody
    public ResponseDTO createClient(@RequestBody ClientDTO clientDTO)
    {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/getclients")
    @ResponseBody
    public List<ClientDTO> getAllClients()
    {
        return clientService.getAllClients();
    }


    @GetMapping("/getclientsfiltered")
    @ResponseBody
    public List<ClientDTO> getAllClientsFilteredByProvince(@RequestBody RequestDTO province)
    {
        return clientService.getAllClientsFilteredByProvince(province);
    }
}
