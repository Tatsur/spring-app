package com.ttsr.springapp.controller;

import com.ttsr.springapp.dto.ProductDto;
import com.ttsr.springapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2

public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ModelAttribute("products")
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/product";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, ModelMap model){
        model.put("productDto",productService.findById(id));
        return "product_form";
    }
    @RequestMapping("/add")
    public String add(){
        return "product_form";
    }

    @PostMapping("/merge")
    public String merge(@Valid @ModelAttribute ProductDto productDto){
        log.debug(productDto.getId()+productDto.getTitle());
        productService.merge(productDto);
        return "redirect:/product";
    }
}
