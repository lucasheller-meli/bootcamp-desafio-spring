package com.desafio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Posters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private Long idSeller;
    private LocalDate date;
    private Integer category;
    @ElementCollection
    private Map<String, String> detail;
    private Double price;
}
