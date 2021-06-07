package com.challenge.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    private String sellerName;
    @ElementCollection
    private Map<Long, String> followed;
    public void AddFollowed(Long userId, String userName){
        this.followed.put(userId, userName);
    }
    public void removeFollowed(Long sellerId, String sellerName){
        this.followed.remove(sellerId, sellerName);
    }
}