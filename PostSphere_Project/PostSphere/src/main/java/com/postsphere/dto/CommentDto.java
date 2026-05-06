package com.postsphere.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private Long postId;
    private Long userId;
}
