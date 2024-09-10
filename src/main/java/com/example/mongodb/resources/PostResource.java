package com.example.mongodb.resources;

import com.example.mongodb.domain.Post;
import com.example.mongodb.resources.util.URL;
import com.example.mongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findPostByTitle(@RequestParam(value = "text", defaultValue = "") String text ) { //para o endpoint identificar o nome do parametro(text)
        text = URL.decodeParam(text);
        List<Post> listPost = postService.findPostByTitle(text);
        return ResponseEntity.ok().body(listPost);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearchPostByStringAnywhere(@RequestParam(value = "text", defaultValue = "") String text,
                                                                     @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                                     @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        //utilizar um auxiliar pois no parametro é passado uma string
        Date minDateAux = URL.convertDate(minDate, new Date(0L));
        Date maxDateAux = URL.convertDate(maxDate, new Date(0L));
        List<Post> listPost = postService.fullPostSearchByStringAnywhere(text, minDateAux, maxDateAux);
        return ResponseEntity.ok().body(listPost);
    }

}
