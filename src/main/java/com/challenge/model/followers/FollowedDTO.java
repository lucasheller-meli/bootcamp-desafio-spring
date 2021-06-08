package com.challenge.model.followers;

import com.challenge.model.user.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FollowedDTO {

    private Integer userId;

    private String userName;

    private List<UserDTO> followed;

}
