package com.example.mongodb.services;

import com.example.mongodb.domain.Post;
import com.example.mongodb.repositories.PostRepository;
import com.example.mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //metodo que retorna um post pelo id
    public Post findPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado"));
    }

}
