package pl.learnspringboot.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter //lombok
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //strategia generowanie klucza głównego, bez tego hibernate przypisał zawsze 0 (domyślna wartość long)
    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
    @OneToMany(cascade = CascadeType.REMOVE) //Aby przy usuwaniu postów usuwane były też komentarze, usuwanie kaskadowe
    @JoinColumn(name = "postId", updatable = false, insertable = false) //hibernate n+1 zapytań / hibarnate nie usuwa komentarzy / nie zmienia przy dodawaniu
    private List<Comment> comment;
}
