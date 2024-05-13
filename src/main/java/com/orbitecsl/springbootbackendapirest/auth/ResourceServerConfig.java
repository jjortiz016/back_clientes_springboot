package com.orbitecsl.springbootbackendapirest.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
   // implementar todas las reglas de seguridad de nuestros empoints de nuestras rutas hacia los recursos de nuestra aplicación.


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/clientes","/api/clientes/page/**", "/api/vehiculos ", "/api/vehiculos/page/**", "/api/uploads/img/**").permitAll() //, "/images/**" se quito esta linea por que no se identifico la ruta
                .antMatchers(HttpMethod.POST, "/api/clientes" ).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/vehiculos/ver/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/clientes/**" ).hasRole("ADMIN")
                .antMatchers("/api/vehiculos/**" ).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/api/clientes/{id}", "/api/vehiculos/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and().cors().configurationSource(corsConfigurationSource());

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); //para todas nuestras rutas del backend
        return source;

    }

    //registrar un filtro
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
