package com.algaworksapi.algaworksapi;

import com.algaworksapi.algaworksapi.config.property.AlgamoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)//TODO: Ativa AlgamoneyApiProperty
public class AlgaworksApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaworksApiApplication.class, args);
	}



//	@Bean
//	  public WebMvcConfigurer corsConfigurer() {
//	    return new WebMvcConfigurer() {
//	      @Override
//	      public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/*").allowedOrigins("http://localhost:8000");
//	      }
//	    };
//	  }
}

