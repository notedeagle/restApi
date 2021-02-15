package pl.learnspringboot.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.learnspringboot.restapi.model.Comment;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.repository.CommentRepository;
import pl.learnspringboot.restapi.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //lombok
public class PostService {

    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE,
                Sort.by(sort, "id")
        )); //Stronnicowanie / Dodajemy sortowanie
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    //Pobieramy postronnicowane posty
    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                )); //Dodajemy sortowanie

        //Lista id z postów
        List<Long> ids = allPosts.stream()
                .map(Post::getId)  //mapujemy posty na id
                .collect(Collectors.toList());

        //Na podstawie id pobieramy komentarze
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);
        //Komentarze do postów
        allPosts.forEach(post -> post.setComment(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                .filter(comment -> comment.getPostId() == id)
                .collect(Collectors.toList());
    }
}
