package com.challenge.model.publication;

import com.challenge.enumx.Category;
import com.challenge.model.product.CreateProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePublicationDTO implements Serializable {

    private Integer userId;

    private LocalDate date;

    private CreateProductDTO detail;

    private Category category;

    private double price;

    private Boolean hasPromo = false;

    private Double discount = 0.0;

}
