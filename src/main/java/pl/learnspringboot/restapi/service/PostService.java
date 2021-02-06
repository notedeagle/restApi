package pl.learnspringboot.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor //lombok
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }
}
