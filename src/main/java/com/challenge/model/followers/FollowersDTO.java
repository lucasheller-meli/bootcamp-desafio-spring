package com.challenge.model.followers;

import com.challenge.model.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowersDTO {

    private Integer userId;

    private String userName;

    private List<UserDTO> followers;
}
