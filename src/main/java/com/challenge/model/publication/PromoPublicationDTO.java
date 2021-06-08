package com.challenge.model.publication;

import com.challenge.model.product.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PromoPublicationDTO {

    private Integer id;

    private LocalDate date;

    private ProductDTO detail;

    private Integer category;

    private double price;

    private boolean hasPromo;

    private Double discount;
}
