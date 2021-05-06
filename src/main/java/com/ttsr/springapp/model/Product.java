package com.ttsr.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAll",query = "select p from Product p"),
        @NamedQuery(name = "deleteById",query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findById",query = "select p from Product p where p.id = :id"),
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private BigDecimal cost;

    @Column
    private Integer version;

    @Column
    private Boolean isDeleted;
}
