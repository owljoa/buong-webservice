package com.buong.springwebservice.service;

import com.buong.springwebservice.domain.posts.Posts;
import com.buong.springwebservice.domain.posts.PostsRepository;
import com.buong.springwebservice.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostsService postService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void save_dto_dataToTable() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                    .author("kim")
                    .content("테스트")
                    .title("테스트 타이틀")
                    .build();

        // when
        postService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }

}
