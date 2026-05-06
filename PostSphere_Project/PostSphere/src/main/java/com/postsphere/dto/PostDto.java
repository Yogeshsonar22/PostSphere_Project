package com.postsphere.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String imageName;
    private LocalDateTime createdAt;
    private Long userId;
    private List<CommentDto> comments;
}
