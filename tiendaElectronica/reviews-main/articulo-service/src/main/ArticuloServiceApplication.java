package com.articulos.articulo_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ArticuloServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticuloServiceApplication.class, args);
	}

}
