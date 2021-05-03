package com.ttsr.springapp.repository;

import com.ttsr.springapp.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final List<Product> products = new CopyOnWriteArrayList<>();
    private final AtomicLong identity = new AtomicLong();

    @PostConstruct
    public void init() {
        products.addAll(List.of(
                new Product(identity.incrementAndGet(), "Shoes", new BigDecimal(2500), 1, false),
                new Product(identity.incrementAndGet(), "T-Shirt", new BigDecimal(1300), 1, false),
                new Product(identity.incrementAndGet(), "Hat", new BigDecimal(3000), 1, false),
                new Product(identity.incrementAndGet(), "Coat", new BigDecimal(4150), 1, false)
        ));
    }

    public List<Product> findAll(){
        return products.stream()
                .filter(p -> !p.getIsDeleted())
                .collect(Collectors.toUnmodifiableList());
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
            products.add(product);
            return product;
        }
        Product storedProduct = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        product.setId(storedProduct.getId());
        product.setVersion(storedProduct.getVersion() + 1);
        products.remove(storedProduct);
        products.add(product);
        return product;
    }

    public int deleteById(Long id){
        List<Product> toDeleteList = products.stream()
                .filter(d -> d.getId().equals(id))
                .collect(Collectors.toList());
        toDeleteList.forEach(d -> d.setIsDeleted(true));
        return toDeleteList.size();
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
