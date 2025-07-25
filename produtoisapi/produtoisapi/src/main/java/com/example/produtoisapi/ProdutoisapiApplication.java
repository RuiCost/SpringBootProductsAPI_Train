package com.example.produtoisapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProdutoisapiApplication {

	@GetMapping("/productTitle")
	public String showProductTitlePage() {
		return "Products are quite important!"; // nome do ficheiro .html ou .jsp em templates/
	}

	public static void main(String[] args) {
		SpringApplication.run(ProdutoisapiApplication.class, args);
	}

}
