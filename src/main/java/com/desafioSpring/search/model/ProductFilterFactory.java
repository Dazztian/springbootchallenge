package com.desafioSpring.search.model;

import com.desafioSpring.search.DTO.ProductDTO;
import com.desafioSpring.search.DTO.searchRequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

public class ProductFilterFactory {

    public static Predicate<ProductDTO> getProductFilter(searchRequestDTO request)
    {
        List<Predicate<ProductDTO>> allPredicates;
        allPredicates= getAllPredicates(request);
        return allPredicates.stream().reduce( p -> true, Predicate::and);
    }

    private static List<Predicate<ProductDTO>> getAllPredicates(searchRequestDTO request)
    {
        List<Predicate<ProductDTO>> allPredicates = new ArrayList<>();

        if(request.getId().isPresent()) {
            allPredicates.add( p -> p.getId() == request.getId().get() );
        }

        if(request.getCategory().isPresent()) {
            allPredicates.add( p -> p.getCategory().equalsIgnoreCase(request.getCategory().get().toLowerCase(Locale.ROOT) ) );
        }

        if(request.getName().isPresent()) {
            allPredicates.add( p -> p.getName().equalsIgnoreCase(request.getName().get().toLowerCase(Locale.ROOT)));
        }

        if(request.getBrand().isPresent()) {
            allPredicates.add( p -> p.getBrand().equalsIgnoreCase(request.getBrand().get().toLowerCase(Locale.ROOT) ) );
        }

        if(request.getPrice().isPresent()) {
            allPredicates.add( p -> p.getPrice() == request.getPrice().get() );
        }
        if(request.getMinPrice().isPresent()) {
            allPredicates.add( p -> p.getPrice() > request.getMinPrice().get() );
        }

        if(request.getMaxPrice().isPresent()) {
            allPredicates.add( p -> p.getPrice() < request.getMaxPrice().get() );

        }
        if(request.getQuantity().isPresent()) {
            allPredicates.add( p -> p.getQuantity() == request.getQuantity().get() );
        }
        if(request.getMinQuantity().isPresent()) {
            allPredicates.add( p -> p.getQuantity() > request.getMinQuantity().get() );
        }
        if(request.getFreeShipping().isPresent()) {
            allPredicates.add( p -> p.getFreeShipping() == request.getFreeShipping().get() );

        }
        if(request.getPrestige().isPresent()) {
            allPredicates.add( p -> p.getPrestige().equalsIgnoreCase(request.getPrestige().get().toLowerCase(Locale.ROOT)) );
        }



        return allPredicates;
    }
}
