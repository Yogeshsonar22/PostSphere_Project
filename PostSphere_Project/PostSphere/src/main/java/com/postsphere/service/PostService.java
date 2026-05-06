package com.postsphere.service;

import com.postsphere.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    PostDto createPost(PostDto postDto, Long userId);
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePost(Long postId);
    PostDto getPostById(Long postId);
    Page<PostDto> getAllPosts(int page, int size, String sortBy);
    PostDto uploadImage(Long postId, MultipartFile file) throws Exception;
}
