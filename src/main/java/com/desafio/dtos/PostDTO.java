package com.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private Long idSaler;
    private String date;
    private Integer category;
    private Map<String, String> detail;
    private Double price;


}
