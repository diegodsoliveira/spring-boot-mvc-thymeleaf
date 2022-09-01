package com.diego.springbootthymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private ImplementacaoUserDetailsService implementacaoUserDetailsService;

  @Override // Configura as solicitações de acesso por Http
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable() // Desativa as configurações padrões de memória
        .authorizeRequests() // permite restringir acessos
        .antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuário acessa a página inicial do sistema
        .antMatchers(HttpMethod.GET, "/listarpessoas").hasAnyRole("ADMIN")
        .anyRequest().authenticated()
        .and().formLogin().permitAll() // permite qualquer usuário
        .loginPage("/login")
        .defaultSuccessUrl("/listarpessoas")
        .failureUrl("/login?error=true")
        .and().logout().logoutSuccessUrl("/login") // Mapeia a url de Logout e invalida usuário autenticado
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
  }

  @Override // Cria autenticação do usuário com banco de dados ou em memória
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(implementacaoUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override // Ignora url específicas
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/materialize-css/**");
  }
}
