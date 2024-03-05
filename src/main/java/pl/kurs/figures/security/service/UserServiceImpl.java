package pl.kurs.figures.security.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kurs.figures.exceptions.EmptyTableException;
import pl.kurs.figures.security.dto.UserDTO;
import pl.kurs.figures.security.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        return Optional.of(userRepository.findAll(pageable)
                .map(user -> modelMapper.map(user, UserDTO.class)))
                .orElseThrow(() -> new EmptyTableException("No users found"));
    }

}
