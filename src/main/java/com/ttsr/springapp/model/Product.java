package com.ttsr.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAll",query = "from Product p"),
        @NamedQuery(name = "deleteById",query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findById",query = "from Product p where p.id = :id"),
        @NamedQuery(name = "findCustomersByProductId",query = "from Customer c join c.products p where p.id=:id"),
        @NamedQuery(name = "findProductsByCustomerId",query = "from Product p join p.customers c where c.id=:id"),
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private BigDecimal cost;

    @ManyToMany
    @JoinTable(
            name = "product_customer",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Customer> customers;

    @Column
    private Integer version;

    @Column
    private Boolean isDeleted;
}
