package pl.kurs.figures.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.kurs.figures.security.dto.UserDTO;

public interface UserService {

    UserDetailsService userDetailsService();
    Page<UserDTO> getAll(Pageable pageable);

}
