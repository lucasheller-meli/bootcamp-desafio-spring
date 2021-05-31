package com.desafio.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User{
    private String userName;
    @Id
    private Integer id;


}
