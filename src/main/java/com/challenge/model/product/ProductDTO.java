package com.challenge.model.product;

import com.challenge.enumx.Type;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductDTO {

    private Integer id;

    private String productName;

    private Type type;

    private String brand;

    private String color;

    private String notes;
}
