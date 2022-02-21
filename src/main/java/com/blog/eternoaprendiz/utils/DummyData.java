package com.blog.eternoaprendiz.utils;

import com.blog.eternoaprendiz.models.Post;
import com.blog.eternoaprendiz.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    private PostRepository postRepository;


    //esta anotação faz com que ao startar a aplicação o codigo savePosts seja executado aos poucos deixar comentado se não toda vez que subir ela vai criar dois posts
    //@PostConstruct
    public void savePosts(){
        List<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setAutor("Rogério Lopes vilela");
        post1.setTitulo("Olá Mundo!!!!!");
        post1.setTexto("Existe uma antiga lenda que o DEV que não faz o olá mundo, nunca será um bom DEV, tudo tem um começo, e o olá mundo é a porta de entrada. Praise the SUN!!");
        post1.setData(LocalDate.now());

        Post post2 = new Post();
        post2.setAutor("Rogério Lopes vilela");
        post2.setTitulo("O eterno Aprendiz");
        post2.setTexto("Após a leitura do livro Diario de um Mago do escritor Paolo Coelho, entendi que nada mais somos do que eternos aprendizes, sempre estamos a aprender algo nessa vida, feliz aquele que aprende, pois quem aprende muda. Praise the SUN!!");
        post2.setData(LocalDate.now().plus(1L , ChronoUnit.DAYS));

        postList.add(post1);
        postList.add(post2);

        for (Post p : postList) {
            Post postSaved = postRepository.save(p);
            System.out.println(postSaved.getId());
        }

    }
}
