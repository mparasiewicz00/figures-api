package pl.kurs.figures.security.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.figures.security.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    UserService userService;



}
