package com.postsphere.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Long userId;
    private String name;
    private String email;
}
