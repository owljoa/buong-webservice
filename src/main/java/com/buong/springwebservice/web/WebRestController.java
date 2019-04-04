package com.buong.springwebservice.web;

<<<<<<< HEAD
import com.buong.springwebservice.domain.posts.PostsRepository;
import com.buong.springwebservice.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsRepository postsRepository;

=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebRestController {
>>>>>>> 68671fb... Initialize Project
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }
<<<<<<< HEAD

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto) {
        postsRepository.save(dto.toEntity());
    }
=======
>>>>>>> 68671fb... Initialize Project
}
