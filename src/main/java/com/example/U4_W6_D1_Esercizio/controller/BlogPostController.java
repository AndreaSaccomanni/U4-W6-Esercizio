package com.example.U4_W6_D1_Esercizio.controller;

import com.example.U4_W6_D1_Esercizio.model.BlogPost;
import com.example.U4_W6_D1_Esercizio.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogPost")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    // Aggiungere un nuovo post
    //POSTMAN -> http:localhost:8080/blogPost/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createBlogPost(@RequestBody BlogPost newBlogPost) {
        Long idGenerato = blogPostService.nuovoBlogPost(newBlogPost);
        return "Il post con id: " + idGenerato + " è stato inserito correttamente";
    }

    // Vedere tutti i post
    //POSTMAN -> http:localhost:8080/blogPost/all
    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
        List<BlogPost> posts = blogPostService.getAllBlogPosts();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    // Ottenere un singolo post tramite id
    //POSTMAN -> http:localhost:8080/blogPost/getById/{id}
    @GetMapping("/getById/{id}")
    public ResponseEntity<String> getSingleBlogPost(@PathVariable long id) {
        Optional<BlogPost> blogPostTrovato = blogPostService.ricercaBlogPostById(id);
        if (blogPostTrovato.isEmpty()) {
            return new ResponseEntity<>("Nessun BlogPost con id: " + id + " trovato",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Blog post con id: "+ id + " trovato con successo", HttpStatus.OK);
        }
    }


    // 4. Modificare uno specifico blog post tramite id
    //POSTMAN -> http:localhost:8080/blogPost/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable long id, @RequestBody BlogPost updatedBlogPost){
        Optional<BlogPost> blogPostTrovato = blogPostService.ricercaBlogPostById(id);
        if(blogPostTrovato.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(updatedBlogPost, HttpStatus.OK);
        }
    }

    // 5. Cancellare uno specifico blog post tramite id
    //POSTMAN -> http:localhost:8080/blogPost/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable int id) {
        boolean removedBlogPost = blogPostService.eliminaBlogPost(id);
        if(removedBlogPost){
            return new ResponseEntity<>("BlogPost eliminato con successo", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("BlogPost non trovato", HttpStatus.NOT_FOUND);
        }
    }


}
