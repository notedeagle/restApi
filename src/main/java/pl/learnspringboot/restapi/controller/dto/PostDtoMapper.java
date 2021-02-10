package pl.learnspringboot.restapi.controller.dto;

import pl.learnspringboot.restapi.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostDtoMapper {

    //Prywatny konstruktor, aby nikt nie stworzył obiektu
    private PostDtoMapper() {
    }

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream()
                .map(PostDtoMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    //Mapowanie postów na postDto, bez komentarzy
    private static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .created(post.getCreated())
                .build();
    }
}
