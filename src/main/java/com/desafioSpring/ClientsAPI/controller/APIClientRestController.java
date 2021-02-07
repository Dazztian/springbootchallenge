package com.desafioSpring.ClientsAPI.controller;


import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.service.ClientService;
import com.desafioSpring.ClientsAPI.service.impl.ClientServiceImpl;
import com.desafioSpring.SalesAPI.purchase.DTO.PurchaseRequestDTO;
import com.desafioSpring.SalesAPI.purchase.DTO.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class APIClientRestController {

    ClientServiceImpl clientService = new ClientServiceImpl();


    @PostMapping("/createclient")
    @ResponseBody
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO)
    {
        return null;
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
