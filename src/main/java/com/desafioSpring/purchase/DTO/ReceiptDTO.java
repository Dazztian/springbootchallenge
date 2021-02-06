package com.desafioSpring.purchase.DTO;

import java.util.List;

public class ReceiptDTO {

    private Integer id;
    private String status;
    private List<ArticleDTO> articles;

    public ReceiptDTO(Integer id, String status, List<ArticleDTO> articles) {
        this.id = id;
        this.status = status;
        this.articles = articles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArticleDTO> getarticles() {
        return articles;
    }

    public void setarticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }
}
