package mx.com.dulceayuda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class DulceAyudaAppApplication implements CommandLineRunner {
	

	private  BCryptPasswordEncoder encripta;

	public static void main(String[] args) {		
		SpringApplication.run(DulceAyudaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		encripta = new BCryptPasswordEncoder();
		System.out.println("Password encoded : " + encripta.encode("dulcenely"));		
	}
	
	

}
