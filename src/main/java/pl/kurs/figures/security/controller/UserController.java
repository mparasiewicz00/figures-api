package pl.kurs.figures.security.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.figures.security.dto.UserDTO;
import pl.kurs.figures.security.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    UserService userService;
    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(
                userService.getAll(PageRequest.of(page, size)));
    }

}
