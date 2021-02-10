package pl.learnspringboot.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.learnspringboot.restapi.controller.dto.PostDto;
import pl.learnspringboot.restapi.controller.dto.PostDtoMapper;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<PostDto> getPosts(@RequestParam(required = false) int page) {  //flaga na false, parametr nie jest wymagany
        int pageNumber = page >= 0 ? page : 0; //aby liczba stron nie była ujemna
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber));
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }
}
