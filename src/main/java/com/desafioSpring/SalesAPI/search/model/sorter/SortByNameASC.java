package com.desafioSpring.SalesAPI.search.model.sorter;

import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;

import java.util.Comparator;

public class SortByNameASC  extends SortMethod implements Comparator<ProductDTO> {

    @Override
    public int compare(ProductDTO o1, ProductDTO o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
