package br.iesb.poo.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	private record IndexRequest(String message) {}

	@GetMapping("/")
	public IndexRequest index() {
		return new IndexRequest("Hello World");
	}
}
