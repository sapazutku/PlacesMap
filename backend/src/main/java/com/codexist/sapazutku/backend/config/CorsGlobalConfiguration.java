package com.codexist.sapazutku.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://658da2f91f7c2b03fcf0def3--codexistcase.netlify.app/");
        config.addAllowedOrigin("https://codexistcase.netlify.app/");
        config.addAllowedOrigin("http://localhost:3000/");
        config.addAllowedOrigin("https://calm-ground-0b381b51e-preview.westus2.4.azurestaticapps.net/");
        config.addAllowedOrigin("https://wonderful-tree-0dda24310.4.azurestaticapps.net/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
