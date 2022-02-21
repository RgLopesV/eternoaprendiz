package com.blog.eternoaprendiz.services.serviceimpl;

import com.blog.eternoaprendiz.models.Post;
import com.blog.eternoaprendiz.repositories.PostRepository;
import com.blog.eternoaprendiz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
