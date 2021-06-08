package com.challenge.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountPromoPostDTO {
        private Long id;
        private String name;
        private Integer countPromoProducts;

}
