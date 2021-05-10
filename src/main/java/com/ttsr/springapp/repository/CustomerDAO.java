package com.ttsr.springapp.repository;

import com.ttsr.springapp.model.Customer;
import com.ttsr.springapp.model.Product;
import com.ttsr.springapp.util.HibernateUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAO {

    private HibernateUtils hibernateUtils;

    @Autowired
    public CustomerDAO(HibernateUtils hibernateUtils){
        this.hibernateUtils = hibernateUtils;
    }

    public List<Customer> findAll() {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            List<Customer> customers = session
                    .createNamedQuery("findAllCustomers",Customer.class)
                    .getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    public Customer findById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            Customer customer = session
                    .createNamedQuery("findCustomerById",Customer.class)
                    .getSingleResult();
            session.getTransaction().commit();
            return customer;
        }
    }

    public Customer saveOrUpdate(Customer customer) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
        return customer;
    }
    public void deleteById(Long id) {
        try(Session session = hibernateUtils.getCurrentSession()){
            session.beginTransaction();
            session.createNamedQuery("deleteCustomerById",Customer.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
