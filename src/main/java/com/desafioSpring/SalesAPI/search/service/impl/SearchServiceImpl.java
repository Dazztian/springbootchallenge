package com.desafioSpring.SalesAPI.search.service.impl;

import com.desafioSpring.SalesAPI.search.DAO.impl.ApiSearchDAOImpl;
import com.desafioSpring.SalesAPI.search.DTO.ProductDTO;
import com.desafioSpring.SalesAPI.search.DTO.searchRequestDTO;
import com.desafioSpring.SalesAPI.search.model.ProductFilterFactory;
import com.desafioSpring.SalesAPI.search.service.SearchService;
import com.desafioSpring.SalesAPI.search.model.sorter.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {


    ApiSearchDAOImpl apiBusqueda = new ApiSearchDAOImpl();

    List<ProductDTO> productList;

    SortMethod sortMethod=null;


    public SearchServiceImpl() {
    }

    @Override
    public List<ProductDTO> getProducts(searchRequestDTO parametros) {

        this.setSortMethod(parametros);

        productList = this.getProductsFiltered(parametros);

        this.sortResults(productList, (Comparator) sortMethod);

        return productList;

    }

    @Override
    public List<ProductDTO> getProductsFiltered(searchRequestDTO parametros)
    {

        List<ProductDTO>  matches = apiBusqueda.database;
        Predicate<ProductDTO> compositeFilterRule;
        compositeFilterRule = ProductFilterFactory.getProductFilter(parametros);


        return matches.stream()
                .filter(compositeFilterRule)
                .collect(Collectors.toList());

    }


    public void setSortMethod(searchRequestDTO parametros)
    {

        if (parametros.getSortMethod().isPresent())
        {
            int num = parametros.getSortMethod().get();

            if(num==0) this.sortMethod= new SortByNameASC();
            if(num==1) this.sortMethod= new SortByNameDESC();
            if(num==2) this.sortMethod= new SortByLowestPrice();
            if(num==3) this.sortMethod= new SortByHighestPrice();
        }
    }


    public void sortResults(List<ProductDTO> productList, Comparator comparator)
    {
        if(sortMethod != null)
        Collections.sort(productList, comparator );
    }
}
