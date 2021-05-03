package com.ttsr.springapp.util;

import com.ttsr.springapp.dto.ProductDto;
import com.ttsr.springapp.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoConverter {

    public static ProductDto productToDto(Product product){
        return new ProductDto(product.getId(),product.getTitle(),product.getCost());
    }

    public static Product dtoToProduct(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getTitle(),productDto.getCost(),1,false);
    }
}
