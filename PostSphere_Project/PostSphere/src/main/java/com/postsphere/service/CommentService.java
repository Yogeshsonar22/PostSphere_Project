package com.postsphere.service;

import com.postsphere.dto.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId, Long userId);
    void deleteComment(Long commentId);
    List<CommentDto> getCommentsByPost(Long postId);
}
