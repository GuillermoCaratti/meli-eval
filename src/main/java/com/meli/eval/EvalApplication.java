package com.meli.eval;

import com.meli.eval.service.MutantFinderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MutantFinderService.class)
public class EvalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvalApplication.class, args);
	}

}