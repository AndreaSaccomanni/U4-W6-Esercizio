package com.example.U4_W6_D1_Esercizio.controller;

import com.example.U4_W6_D1_Esercizio.model.BlogPost;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogPost")
public class BlogPostController {

    private List<BlogPost> allBlogPost = new ArrayList<>();

    // Vedere tutti i post
    //POSTMAN -> http:localhost:8080/blogPost/all
    @GetMapping("/all")
    public List<BlogPost> getAllBlogPost(){
        return allBlogPost;
    }

    // Vedere un singolo post
    //POSTMAN -> http:localhost:8080/blogPost/getById/{id}
    @GetMapping("/getById/{id}")
    public BlogPost  getSingleBlogPost(@PathVariable int id){
        return allBlogPost.stream()
                .filter(blogPost -> blogPost.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("BlogPost con id " + id + " non trovato"));
    }

    // Aggiungere un nuovo post
    //POSTMAN -> http:localhost:8080/blogPost/create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody BlogPost newPost) {
        BlogPost blogPost = new BlogPost(
                newPost.getCategoria(),
                newPost.getTitolo(),
                newPost.getContenuto(),
                newPost.getTempoDiLettura()
        );
        allBlogPost.add(blogPost);
        return blogPost;
    }

    // 4. Modificare uno specifico blog post tramite id
    //POSTMAN -> http:localhost:8080/blogPost/update/{id}
    @PutMapping("/update/{id}")
    public BlogPost updateBlogPost(@PathVariable int id, @RequestBody BlogPost updatedPost) {
        Optional<BlogPost> existingPost = allBlogPost.stream()
                .filter(blogPost -> blogPost.getId() == id)
                .findFirst();

        if (existingPost.isPresent()) {
            BlogPost blogPost = existingPost.get();
            blogPost.setCategoria(updatedPost.getCategoria());
            blogPost.setTitolo(updatedPost.getTitolo());
            blogPost.setContenuto(updatedPost.getContenuto());
            blogPost.setTempoDiLettura(updatedPost.getTempoDiLettura());
            blogPost.setCover(updatedPost.getCover());
            return blogPost;
        }
        return null;
    }

    // 5. Cancellare uno specifico blog post tramite id
    //POSTMAN -> http:localhost:8080/blogPost/delete/{id}
    @DeleteMapping("/delete/{id}")
    public String deleteBlogPost(@PathVariable int id) {
        boolean removed = allBlogPost.removeIf(blogPost -> blogPost.getId() == id);
        return removed ? "BlogPost eliminato con successo." : "BlogPost non trovato.";
    }



}
