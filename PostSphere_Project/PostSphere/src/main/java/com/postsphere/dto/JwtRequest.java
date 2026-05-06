package com.postsphere.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String email;
    private String password;
}
