package com.postsphere.service;

import com.postsphere.dto.PostDto;
import com.postsphere.entity.*;
import com.postsphere.exception.ResourceNotFoundException;
import com.postsphere.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public PostDto createPost(PostDto postDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setImageName("default.png");

        Post saved = postRepository.save(post);
        return modelMapper.map(saved, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return modelMapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public Page<PostDto> getAllPosts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return postRepository.findAll(pageable)
                .map(post -> modelMapper.map(post, PostDto.class));
    }

    @Override
    public PostDto uploadImage(Long postId, MultipartFile file) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        // timestamp prefix avoids filename collisions
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(file.getInputStream(),
                uploadPath.resolve(fileName),
                StandardCopyOption.REPLACE_EXISTING);

        post.setImageName(fileName);
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }
}
