package com.example.mongodb.services;

import com.example.mongodb.domain.Post;
import com.example.mongodb.repositories.PostRepository;
import com.example.mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //metodo que retorna um post pelo id
    public Post findPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findPostByTitle(String text) {
        return postRepository.findPostByTitle(text);
    }

    public List<Post> fullPostSearchByStringAnywhere(String text, Date minDate, Date maxDate) {
        //macete para ajustar o maxDate, pois ele é setado 00:00 do dia atual
        maxDate = new Date(maxDate.getTime() + 24*60*60*1000); //compilador chora com essas multiplicaçôes kjkkkkk
        return  postRepository.fullPostSearchByStringAnywhere(text, minDate, maxDate);
    }

}
