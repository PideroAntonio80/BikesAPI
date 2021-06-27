package com.sanvalero.bikes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Creado por @ author: Pedro Orós
 * el 18/06/2021
 */

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //definición roles y usuarios
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("{noop}user1") //lo de {noop} se pone para no obligar a usar mecanismo de encriptación
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN");

        /*lo siguiente sería para el caso de que
		 * quisiéramos encriptar la password:
		String pw1=new BCryptPasswordEncoder().encode("user1");
		System.out.println(pw1);
		  auth
	        .inMemoryAuthentication()
	        .withUser("user1")
	          .password("{bcrypt}"+pw1)
	          //.password(pw1)
	          .roles("USER")
	          .and()
	        .withUser("admin")
	          .password(new BCryptPasswordEncoder().encode("admin"))
	          .roles("USER", "ADMIN");*/

		/*la seguiente configuración será para el caso de
		 * usuarios en una base de datos
		 * auth.jdbcAuthentication().dataSource(dataSource)
        	.usersByUsernameQuery("select username, password, enabled"
            	+ " from users where username=?")
        	.authoritiesByUsernameQuery("select username, authority "
            	+ "from authorities where username=?");
		 */
    }

    //definición de políticas de seguridad
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //solo los miembros del rol admin podrán realizar altas
                //y para las demás consultas, solo tendrán que estar autenticados
                /*.antMatchers(HttpMethod.POST, "/bikes/**").hasRole("ADMIN")  <-- Investigar por qué no funciona así*/
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                //.antMatchers("/bikes/**").authenticated()  <-- Investigar qué pasa con esta forma
                .and()
                .httpBasic();
    }
}
