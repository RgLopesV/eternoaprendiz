package com.blog.eternoaprendiz.controller;


import com.blog.eternoaprendiz.models.Post;
import com.blog.eternoaprendiz.services.PostService;
import com.blog.eternoaprendiz.dtos.PostDto;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Optional<Post> postOptional= postService.findById(id);
        modelAndView.addObject("post" , postOptional.get());
        return  modelAndView;
    }

    @RequestMapping(value = "/updatepost/{id}" , method = RequestMethod.GET)
    public ModelAndView updatePostForm(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView("postAtualizaForm");
        Optional<Post> postOptional= postService.findById(id);
        modelAndView.addObject("post" , postOptional.get());
        return modelAndView;
    }

    @RequestMapping(value = "/updatepost/{id}" , method = RequestMethod.POST)
    public String updatePost(@PathVariable("id") long id,@Validated PostDto postDto, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Campo obrigatório não preenchido!");
            return "redirect:/postAtualizaForm";
        }
        Optional<Post> postOptional = postService.findById(id);
        postOptional.get().setTitulo(postDto.getTitulo());
        postOptional.get().setAutor(postDto.getAutor());
        postOptional.get().setTexto(postDto.getTexto());
        postService.save(postOptional.get());

        attributes.addFlashAttribute("mensagem", "Post Atualizado");
        return  "redirect:/posts/"+id;
    }

    @RequestMapping(value = "/newpost" , method = RequestMethod.GET)
    public String getPostForm(){
        return "postForm";
    }

    @RequestMapping(value = "/newpost" , method = RequestMethod.POST)
    public String savePost(@Validated PostDto postDto, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Campo obrigatório não preenchido!");
            return "redirect:/newpost";
        }
        var post = new Post();
        BeanUtils.copyProperties(postDto , post);
        post.setData(LocalDate.now());

        postService.save(post);
        return  "redirect:/posts";
    }
    @RequestMapping(value = "posts/{id}/deletar" , method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") long id , RedirectAttributes attributes){
            postService.delete(id);
            attributes.addFlashAttribute("mensagem", "Post Apagado!");
            return  "redirect:/posts";
    }
}
