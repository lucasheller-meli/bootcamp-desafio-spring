package com.desafio.dtos;

import com.desafio.entities.PromoPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPromoPostDTO {
    private Long idUser;
    private String name;
    private List<PromoPost> posters;
}
