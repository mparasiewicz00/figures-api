package pl.kurs.figures.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kurs.figures.security.service.UserService;
import pl.kurs.figures.security.service.UserServiceImpl;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


}
