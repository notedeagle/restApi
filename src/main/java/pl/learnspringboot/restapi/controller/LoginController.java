package pl.learnspringboot.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.learnspringboot.restapi.config.LoginCredentials;

@RestController
public class LoginController {

    //Metoda do wystawianie endpointu w swaggerze, aby można się było zalogować
    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
