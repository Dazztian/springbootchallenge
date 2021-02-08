package com.desafioSpring.ClientsAPI.model.utils;

import com.desafioSpring.ClientsAPI.DTO.ClientDTO;

public class EqualClients {

    public static boolean areEquals(ClientDTO c1, ClientDTO c2)
    {
        return (c1.getId() == c2.getId() &&
        c1.getUsername().equals(c2.getUsername()) &&
        c1.getBirthdate().equals(c2.getBirthdate()) &&
        c1.getProvince().equals(c2.getProvince()));
    }
}
