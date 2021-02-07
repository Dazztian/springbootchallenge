package com.desafioSpring.ClientsAPI.DTO;

public class ClientDTO {

    private int id;
    private String username;
    private String birthdate;
    private String province;

    public ClientDTO() {
    }

    public ClientDTO(int id, String username, String birthdate, String province) {
        this.id = id;
        this.username = username;
        this.birthdate = birthdate;
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setPovince(String province) {
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
