package com.desafioSpring.search.DTO;


import java.util.List;
import java.util.Optional;

public class searchRequestDTO {

    private Optional<Integer> id;
    private Optional<String> category;
    private Optional<String> name;
    private Optional<String> brand;
    private Optional<Integer> price;
    private Optional<Integer> minPrice;
    private Optional<Integer> maxPrice;
    private Optional<Integer> quantity;
    private Optional<Integer> minQuantity;
    private Optional<Boolean> freeShipping;
    private Optional<String> prestige;
    private Optional<List<String>> filter2;
    private Optional<Integer> sortMethod;


    public static searchRequestDTO createSearchRequestDTOOnlyWithId(Integer id)
    {
        return new searchRequestDTO(Optional.of(id), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),
                Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),
                Optional.empty());
    }

    public searchRequestDTO(Optional<Integer> id, Optional<String> category, Optional<String> name, Optional<String> brand, Optional<Integer> price, Optional<Integer> minPrice, Optional<Integer> maxPrice, Optional<Integer> quantity, Optional<Integer> minQuantity, Optional<Boolean> freeShipping, Optional<String> prestige, Optional<List<String>> filter2, Optional<Integer> sortMethod) {

        this.id = id;
        this.category = category;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.freeShipping = freeShipping;
        this.prestige = prestige;
        this.filter2 = filter2;
        this.sortMethod = sortMethod;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(Optional<Integer> id) {
        this.id = id;
    }

    public Optional<String> getPrestige() {
        return prestige;
    }

    public void setPrestige(Optional<String> prestige) {
        this.prestige = prestige;
    }

    public Optional<String> getCategory() {
        return category;
    }

    public void setCategory(Optional<String> category) {
        this.category = category;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getBrand() {
        return brand;
    }

    public void setBrand(Optional<String> brand) {
        this.brand = brand;
    }

    public Optional<Integer> getPrice() {
        return price;
    }

    public void setPrice(Optional<Integer> price) {
        this.price = price;
    }

    public Optional<Integer> getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Optional<Integer> minPrice) {
        this.minPrice = minPrice;
    }

    public Optional<Integer> getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Optional<Integer> maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Optional<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(Optional<Integer> quantity) {
        this.quantity = quantity;
    }

    public Optional<Integer> getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Optional<Integer> minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Optional<Boolean> getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Optional<Boolean> freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Optional<List<String>> getFilter2() {
        return filter2;
    }

    public void setFilter2(Optional<List<String>> filter2) {
        this.filter2 = filter2;
    }

    public Optional<Integer> getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(Optional<Integer> sortMethod) {
        this.sortMethod = sortMethod;
    }

}
