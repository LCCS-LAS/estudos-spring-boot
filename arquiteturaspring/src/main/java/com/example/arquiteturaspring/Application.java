package com.example.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);//Outro de jeito de iniciar o projeto
		builder.bannerMode(Banner.Mode.OFF);//Desligar banner
		builder.profiles("produção");//Trocar de perfil
		builder.run(args);
	}

}
