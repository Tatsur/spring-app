package com.ttsr.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "findAllCustomers",query = "select c from Customer c"),
        @NamedQuery(name = "deleteCustomerById",query = "delete from Customer c where c.id = :id"),
        @NamedQuery(name = "findCustomerById",query = "select c from Customer c where c.id = :id"),
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "products")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "product_customer",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;

    @Column
    private Integer version;

    @Column
    private Boolean isDeleted;
}
