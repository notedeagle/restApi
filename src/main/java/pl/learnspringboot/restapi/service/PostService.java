package pl.learnspringboot.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import pl.learnspringboot.restapi.model.Comment;
import pl.learnspringboot.restapi.model.Post;
import pl.learnspringboot.restapi.repository.CommentRepository;
import pl.learnspringboot.restapi.repository.PostRepository;

import javax.transaction.Transactional;
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

    //Dodawanie postów
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    //Edytowanie postów
    @Transactional  //jedna transakcja, a nie dwie
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setContent(post.getContent());
        return postEdited; //hibernate sam zarządza edytowanymi encjami, nie trzeba tak: postRepository.save(post);
    }

    //Usuwanie posta
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
