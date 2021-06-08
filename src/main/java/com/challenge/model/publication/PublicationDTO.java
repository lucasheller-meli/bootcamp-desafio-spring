package com.challenge.model.publication;

import com.challenge.model.product.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PublicationDTO {

    private Integer id;

    private LocalDate date;

    private ProductDTO detail;

    private Integer category;

    private double price;

}
