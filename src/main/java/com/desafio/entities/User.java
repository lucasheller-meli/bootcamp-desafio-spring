package com.desafio.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Map;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private String userName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @ElementCollection
    private Map<Long, String> following;


}
