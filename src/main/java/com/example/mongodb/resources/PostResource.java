package com.example.mongodb.resources;

import com.example.mongodb.domain.Post;
import com.example.mongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable String id) {
        Post postObj = postService.findPostById(id);
        return ResponseEntity.ok().body(postObj);
    }

}
