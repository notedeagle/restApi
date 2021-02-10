package pl.learnspringboot.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.learnspringboot.restapi.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //Własne metody w Spring Data JPA
    //@Query("select p from Post p where title = :title") //tylko dla skomplikowanych zapytań, JPA query creation
    //List<Post> findByTitle(String title);

    @Query("select p from Post p left join fetch p.comment") //Zapytanie JPA / Left join aby pobrały się też posty bez komentarzy
    List<Post> findAllPosts(Pageable page); //Stronnicowanie
}
