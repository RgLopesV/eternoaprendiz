package com.blog.eternoaprendiz.services;

import com.blog.eternoaprendiz.models.Post;

import java.util.List;

public interface PostService {

   List<Post> findAll();
   Post findById(long id);
   Post save(Post post);


}
