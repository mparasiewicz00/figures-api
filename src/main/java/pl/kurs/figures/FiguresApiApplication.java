package pl.kurs.figures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;

@SpringBootApplication
@EnableEnversRepositories
public class FiguresApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FiguresApiApplication.class, args);
    }

}
