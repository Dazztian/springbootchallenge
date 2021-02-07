package com.desafioSpring.ClientsAPI.DTO;

public class RequestDTO {

    public String province;

    public RequestDTO() {
    }

    public RequestDTO(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
