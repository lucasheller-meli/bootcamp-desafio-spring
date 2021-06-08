package com.challenge.entity;

import com.challenge.enumx.Type;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "name")
    private String productName;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "notes")
    private String notes;

    @Column(name = "discount")
    private Double discount = 0.0;

    @Column(name = "promotional")
    private Boolean hasPromo = false;

}
