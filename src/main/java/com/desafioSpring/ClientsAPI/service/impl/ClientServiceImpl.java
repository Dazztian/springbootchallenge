package com.desafioSpring.ClientsAPI.service.impl;

import com.desafioSpring.ClientsAPI.DAO.impl.APIClientsDAOImpl;
import com.desafioSpring.ClientsAPI.DTO.ClientDTO;
import com.desafioSpring.ClientsAPI.DTO.RequestDTO;
import com.desafioSpring.ClientsAPI.DTO.ResponseDTO;
import com.desafioSpring.ClientsAPI.model.utils.EqualClients;
import com.desafioSpring.ClientsAPI.model.utils.WriteClientJSON;
import com.desafioSpring.ClientsAPI.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    APIClientsDAOImpl apiClientsDAO= new APIClientsDAOImpl();

    List<ClientDTO> clientList=this.getAllClients();;

    @Override
    public ResponseDTO createClient(ClientDTO clientDTO)
    {
        //Primero checkeamos todos los casos que NO nos permitirían crear un cliente para cortar la ejecución
        if(this.clientAlreadyExists(clientDTO))
        {
            return new ResponseDTO(clientList, "400", "No se pudo crear el cliente, ya existe en el sistema");

        }
        if(this.clientIdAlreadyExists(clientDTO))
        {
            return new ResponseDTO(clientList, "400", "No se pudo crear el cliente, ya existe en usuario con ese id en el sistema");

        }
        clientList.add(clientDTO);
        WriteClientJSON.writeClients(clientList);

        return new ResponseDTO(clientList, "200", "Operación exitosa");
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

    @Override
    public  boolean clientAlreadyExists(ClientDTO clientDTO) {
        return     apiClientsDAO.getAllClients().stream().anyMatch( client-> EqualClients.areEquals(clientDTO, client));
    }

    @Override
    public boolean clientIdAlreadyExists(ClientDTO clientDTO) {
        return     apiClientsDAO.getAllClients().stream().anyMatch( client-> client.getId() == clientDTO.getId());
    }

}
