package com.desafio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Posters {
    @Id
    private Long idPost; //como gerar isso automaticamente
    private Long idSaler;
    private String date;
    private Integer category;
    @ElementCollection
    private Map<String, String> detail;
    private Double price;
}
