package com.challenge.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetailerPromoProductsDTO {

    private Integer userId;

    private String userName;

    private Integer promoproducts_count;

}
