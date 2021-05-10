package com.ttsr.springapp.repository;

import com.ttsr.springapp.configuration.EntityManagerInstance;
import com.ttsr.springapp.model.Customer;
import com.ttsr.springapp.model.Product;
import com.ttsr.springapp.util.HibernateUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO {

    private HibernateUtils hibernateUtils;

    @Autowired
    public ProductDAO(HibernateUtils hibernateUtils){
        this.hibernateUtils = hibernateUtils;
    }

    public List<Product> findAll() {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session
                    .createNamedQuery("findAll",Product.class)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product findById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            Product product = session
                    .createNamedQuery("findById",Product.class)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Customer> findCustomersByProductId(){
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Customer> customer = session
                    .createNamedQuery("findCustomersByProductId",Customer.class)
                    .getResultList();
            session.getTransaction().commit();
            return customer;
        }
    }

    public List<Product> findProductsByCustomerId(){
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Product> products = session
                    .createNamedQuery("findProductsByCustomerId",Product.class)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product saveOrUpdate(Product product) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }
    public void deleteById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            session.createNamedQuery("deleteById",Product.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
