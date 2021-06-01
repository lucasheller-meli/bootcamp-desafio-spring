package com.desafio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private String nameSaler;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSaler;
    @ElementCollection
    private Map<Long, String> followers;


}
