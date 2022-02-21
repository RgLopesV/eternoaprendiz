package com.blog.eternoaprendiz.services;

import com.blog.eternoaprendiz.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

   List<Post> findAll();
   Optional<Post> findById(long id);
   Post save(Post post);
   void delete(long id);
}
