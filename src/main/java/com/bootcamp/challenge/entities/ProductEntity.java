package com.bootcamp.challenge.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(length = 120, nullable = false)
    private String name;

    @Column(length = 55, nullable = false)
    private String type;

    @Column(length = 30, nullable = false)
    private String brand;

    @Column(length = 30, nullable = false)
    private String color;

    @Column(length = 150, nullable = false)
    private String notes;
}
