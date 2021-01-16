package br.com.gok.templateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@EnableTransactionManagement
@EnableScheduling
public class TemplateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApiApplication.class, args);
	}


}
