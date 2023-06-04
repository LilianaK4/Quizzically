package pl.lilianakrol.quizzically;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaRepositories
@SpringBootApplication
@EnableAsync
@EntityScan("pl.lilianakrol.quizzically.models")
public class QuizzicallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizzicallyApplication.class, args);
    }

}
