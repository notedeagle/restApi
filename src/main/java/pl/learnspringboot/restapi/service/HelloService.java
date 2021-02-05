package pl.learnspringboot.restapi.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")   //domyślny, nie trzeba go ustawiać
public class HelloService {
    public String hello() {
        return "Hello World";
    }
}
