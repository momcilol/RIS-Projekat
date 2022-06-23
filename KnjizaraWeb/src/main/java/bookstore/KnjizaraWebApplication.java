package bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("model")
public class KnjizaraWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnjizaraWebApplication.class, args);
	}

}
