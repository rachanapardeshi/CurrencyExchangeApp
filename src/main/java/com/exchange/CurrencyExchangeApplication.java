package com.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication(exclude = CacheAutoConfiguration.class)
@EnableAutoConfiguration(exclude = { RepositoryRestMvcAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableCaching
public class CurrencyExchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }
    
    
    @Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Currency Exchange").description("Currency Exchange")
				.version("V3").contact(new Contact().name("RAchana Pardeshi").email("rachanapardeshi21@gmail.com")));
	}

}
