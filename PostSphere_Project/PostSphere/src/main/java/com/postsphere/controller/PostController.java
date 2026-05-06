package com.postsphere.controller;

import com.postsphere.dto.PostDto;
import com.postsphere.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                               @PathVariable Long userId) {
        return ResponseEntity.ok(postService.createPost(postDto, userId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                               @PathVariable Long postId) {
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(postService.getAllPosts(page, size, sortBy));
    }

    @PostMapping("/{postId}/image")
    public ResponseEntity<PostDto> uploadImage(@PathVariable Long postId,
                                                @RequestParam("image") MultipartFile file) throws Exception {
        return ResponseEntity.ok(postService.uploadImage(postId, file));
    }
}
