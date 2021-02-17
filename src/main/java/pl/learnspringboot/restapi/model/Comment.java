package pl.learnspringboot.restapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //strategia generowanie klucza głównego, bez tego hibernate przypisał zawsze 0 (domyślna wartość long)
    private long id;
    private long postId;
    private String content;
    private LocalDateTime created;
}
