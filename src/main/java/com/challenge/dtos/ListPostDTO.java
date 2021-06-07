package com.challenge.dtos;

import com.challenge.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPostDTO {
    private Long userId;
    private String userName;
    private List<Product> list;
}
