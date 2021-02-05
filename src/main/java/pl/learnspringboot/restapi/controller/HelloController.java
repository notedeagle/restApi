package pl.learnspringboot.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.learnspringboot.restapi.service.HelloService;

//Można dodać @RequiredArgsConstructor (lombok), nie trzeba wtedy tworzyć konstruktora
@RestController
public class HelloController {

    //Wstrzykiwanie przez pole, adnotacje lub konstruktor
    //Najlepiej przez konstruktor

    private final HelloService helloService;

    //Adnotacja @Autowired już nie jest potrzebna
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String hello() {
        return helloService.hello();
    }
}
