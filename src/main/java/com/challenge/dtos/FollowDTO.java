package com.challenge.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDTO {
    private Long id;
    private String name;
    private Map<Long, String> list;
}
