package com.diego.springbootthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = "com.diego.springbootthymeleaf.model")
@ComponentScan(basePackages = { "com.*" })
@EnableJpaRepositories(basePackages = { "com.diego.springbootthymeleaf.repository" })
@EnableTransactionManagement
@EnableWebMvc
public class SpringBootThymeleafApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootThymeleafApplication.class, args);

  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("/login");
    registry.setOrder(Ordered.LOWEST_PRECEDENCE);
  }
}
