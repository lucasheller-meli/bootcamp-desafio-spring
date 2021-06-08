package com.challenge.entity;

import com.challenge.enumx.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publication")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Retailer retailer;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "price")
    private double price;

    @Column(name = "promo")
    private boolean hasPromo = false;

    @Column(name = "discount")
    private Double discount = 0.0;
}
