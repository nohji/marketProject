package zerobase.marketproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketProjectApplication.class, args);
    }

}
