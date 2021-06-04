package com.desafio.dtos;

import com.desafio.entities.Posters;
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
    private Long idUser;
    private String name;
    private List<Posters> posters;


}
