package com.blog.eternoaprendiz.controller;


import com.blog.eternoaprendiz.models.Post;
import com.blog.eternoaprendiz.services.PostService;
import dtos.PostDto;
import org.apache.juli.logging.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;
import java.time.LocalDate;
import java.util.List;

@Controller

public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/posts" , method = RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView modelAndView = new ModelAndView( "posts");
        List<Post>  posts = postService.findAll();
        modelAndView.addObject("posts", posts);
        return  modelAndView;
    }

    @RequestMapping(value = "posts/{id}" , method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("postDetails");
        Post post = postService.findById(id);
        modelAndView.addObject("post" , post);
        return  modelAndView;
    }


    @RequestMapping(value = "/newpost" , method = RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }


    @RequestMapping(value = "/newpost" , method = RequestMethod.POST)
    public String savePost(@Validated PostDto postDto, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "campo obrigatório não preenchido");
            return "redirect:/newpost";
        }
        var post = new Post();
        BeanUtils.copyProperties(postDto , post);
        post.setData(LocalDate.now());

        postService.save(post);
        return  "redirect:/posts";
    }

}
