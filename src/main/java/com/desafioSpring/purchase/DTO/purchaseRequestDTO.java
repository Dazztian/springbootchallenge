package com.desafioSpring.purchase.DTO;


import java.util.List;

public class purchaseRequestDTO {

    private String username;
    private List<ArticleDTO> articles;

    public purchaseRequestDTO(String username, List<ArticleDTO> articles) {
        this.username = username;
        this.articles = articles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }
}
