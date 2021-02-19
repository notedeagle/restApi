package pl.learnspringboot.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser                   //testujemy tak jakby apliakcja nie była zabezpieczona tokenem
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;

    //Z MockMvc, bez jsonpath
    @Test
    @Transactional              //po teście będzie rollback, nie doda testowego posta do bazy
    void shouldGetSinglePost() throws Exception {
        //given
        Post newPost = new Post();
        newPost.setTitle("Test post dodany");
        newPost.setContent("Test tresc dodany");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/posts/" + newPost.getId()))
                .andDo(print())
                .andExpect(status().is(200))            //status 200
                .andReturn();//czy wartośc jest poprawna
        //then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(newPost.getId());
        assertThat(post.getTitle()).isEqualTo(newPost.getTitle());
        assertThat(post.getContent()).isEqualTo(newPost.getContent());
    }
}