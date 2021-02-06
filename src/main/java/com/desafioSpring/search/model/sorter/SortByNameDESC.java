
package com.desafioSpring.search.model.sorter;

import com.desafioSpring.search.DTO.ProductDTO;

import java.util.Comparator;

public class SortByNameDESC extends SortMethod implements Comparator<ProductDTO>
{
        @Override
        public int compare(ProductDTO o1, ProductDTO o2) {
            return o2.getName().compareTo(o1.getName());
        }

}
