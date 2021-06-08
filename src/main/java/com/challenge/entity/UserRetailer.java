package com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "followers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRetailer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

}
