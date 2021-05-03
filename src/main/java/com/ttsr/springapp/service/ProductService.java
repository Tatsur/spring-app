package com.ttsr.springapp.service;

import com.ttsr.springapp.dto.ProductDto;
import com.ttsr.springapp.repository.ProductRepository;
import com.ttsr.springapp.util.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long merge(ProductDto productDto){
        return productRepository.saveOrUpdate(DtoConverter.dtoToProduct(productDto)).getId();
    }

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream()
                .map(DtoConverter::productToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Object findById(Long id){
        return productRepository.findById(id);
    }
}
