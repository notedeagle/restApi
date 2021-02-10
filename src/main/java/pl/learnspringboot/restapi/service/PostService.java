package pl.learnspringboot.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor //lombok
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;

    public List<Post> getPosts(int page) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE)); //Stronnicowanie
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }
}
