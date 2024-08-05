package com.example.blog.rest.api.service.Imp;

import com.example.blog.rest.api.entity.Post;
import com.example.blog.rest.api.exception.ResourceNotFoundException;
import com.example.blog.rest.api.payload.PostResponse;
import com.example.blog.rest.api.repository.CommentRepository;
import com.example.blog.rest.api.repository.PostRepository;
import com.example.blog.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postList);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
    }

    @Override
    public Post updatePost(Long postId, Post post) {
        Post foundPost = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        foundPost.setTitle(post.getTitle());
        foundPost.setContent(post.getContent());
        foundPost.setDescription(post.getDescription());

        return postRepository.save(foundPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        postRepository.deleteById(postId);
    }
}
