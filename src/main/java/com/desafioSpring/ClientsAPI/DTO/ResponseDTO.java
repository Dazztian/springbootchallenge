package com.desafioSpring.ClientsAPI.DTO;

import java.util.List;

public class ResponseDTO {

    private String status;
    private String message;
    private List<ClientDTO> clientlist;


    public ResponseDTO(List<ClientDTO> clientlist, String status, String message) {
        this.clientlist = clientlist;
        this.status = status;
        this.message = message;
    }

    public ResponseDTO() {
    }

    public List<ClientDTO> getClientlist() {
        return clientlist;
    }

    public void setClientlist(List<ClientDTO> clientlist) {
        this.clientlist = clientlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
