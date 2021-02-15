package pl.learnspringboot.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
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
    public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {  //flaga na false, parametr nie jest wymagany / Integer ponieważ "opcjonalny"
        int pageNumber = page != null && page >= 0 ? page : 0; //aby liczba stron nie była ujemna       // parametr page nie może być nullem
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;                        //parametr direction nie może być nullem, ustawiamy domyślnie na ASC
        return PostDtoMapper.mapToPostDtos(postService.getPosts(pageNumber, sortDirection));
    }

    //Pobranie postów i komentarzy, bez problemu n+1, tylko 2 zapytania
    @GetMapping("/posts/comments")
    public List<Post> getPostsWithComments(@RequestParam(required = false) Integer page, Sort.Direction sort) {  //flaga na false, parametr nie jest wymagany
        int pageNumber = page != null && page >= 0 ? page : 0; //aby liczba stron nie była ujemna
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC; //parametr direction nie może być nullem, ustawiamy domyślnie na ASC
        return postService.getPostsWithComments(pageNumber, sortDirection);
    }

    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id) {
        return postService.getSinglePost(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }
}
