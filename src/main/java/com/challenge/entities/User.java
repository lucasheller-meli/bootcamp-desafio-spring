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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    @ElementCollection
    private Map<Long, String> follower;
    public void AddFollower(Long sellerId, String sellerName){
        this.follower.put(sellerId, sellerName);
    }
    public void removeFollower(Long sellerId, String sellerName){
        this.follower.remove(sellerId, sellerName);
    }
}