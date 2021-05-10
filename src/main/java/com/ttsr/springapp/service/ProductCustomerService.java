package com.ttsr.springapp.service;

import com.ttsr.springapp.dto.CustomerDto;
import com.ttsr.springapp.dto.ProductDto;
import com.ttsr.springapp.repository.ProductDAO;
import com.ttsr.springapp.util.DtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCustomerService {

    private final ProductDAO productDAO;

    private final CustomerDto customerDto;

    public List<CustomerDto> findCustomersByProductId(){
        return productDAO.findCustomersByProductId().stream()
                .map(DtoConverter::customerToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findProductsByCustomerId(){
        return productDAO.findProductsByCustomerId().stream()
                .map(DtoConverter::productToDto)
                .collect(Collectors.toList());
    }
}
