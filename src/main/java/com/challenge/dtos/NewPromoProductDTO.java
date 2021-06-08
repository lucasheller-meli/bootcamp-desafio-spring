package com.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPromoProductDTO {
    private Long idPost;
    private Long sellerId;
    private String date;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}
