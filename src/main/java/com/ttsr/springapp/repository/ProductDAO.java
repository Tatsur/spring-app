package com.ttsr.springapp.repository;

import com.ttsr.springapp.configuration.EntityManagerInstance;
import com.ttsr.springapp.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDAO {

    @PersistenceContext(unitName = "ds")
    EntityManager em = EntityManagerInstance.getEntityManager();

    public List<Product> findAll() {
        return em.createNamedQuery("findAll",Product.class)
                .getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class,id);
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
        return product;
    }
    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id",id)
                .executeUpdate();
    }
}
