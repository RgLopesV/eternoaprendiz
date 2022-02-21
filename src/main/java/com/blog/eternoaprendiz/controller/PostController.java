package com.blog.eternoaprendiz.controller;


import com.blog.eternoaprendiz.models.Post;
import com.blog.eternoaprendiz.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView modelAndView = new ModelAndView( "posts");
        List<Post>  posts = postService.findAll();
        modelAndView.addObject("posts", posts);
        return  modelAndView;
    }

}
