package com.example.U4_W6_D1_Esercizio.service;

import com.example.U4_W6_D1_Esercizio.model.BlogPost;
import com.example.U4_W6_D1_Esercizio.repository.BlogPostDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostDAORepository blogPostRepository;

    //creare un post
    public Long nuovoBlogPost(BlogPost nuovoBlogPost){
        BlogPost blogPostInserito = blogPostRepository.save(nuovoBlogPost);
        return blogPostInserito.getId();
    }

    //ricerca post tramite id
    public Optional<BlogPost> ricercaBlogPostById(long id){
        return blogPostRepository.findById(id);
    }

    //update post
    public Optional<BlogPost> updateBlogPost(long id, BlogPost blogPostAggiornato){
        return blogPostRepository.findById(id).map(blogPost -> {
            blogPost.setCategoria(blogPostAggiornato.getCategoria());
            blogPost.setTitolo(blogPostAggiornato.getTitolo());
            blogPost.setContenuto(blogPostAggiornato.getContenuto());
            blogPost.setTempoDiLettura(blogPostAggiornato.getTempoDiLettura());
            return blogPostRepository.save(blogPost);
        });
    }

    //delete post
    public boolean eliminaBlogPost(long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //tutti i post
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }


}
