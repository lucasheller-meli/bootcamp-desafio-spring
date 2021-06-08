package com.challenge.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String userId;

    private String userName;

}
