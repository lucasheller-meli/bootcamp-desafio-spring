package com.challenge.entity;

import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
@Builder
public class Client extends User {

    public Client() {

    }
}
