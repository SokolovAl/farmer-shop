package ru.aston.farmershop.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Collection;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "type")
    private Collection<Product> products;
}
