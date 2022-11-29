package ru.aston.farmershop.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableJpaRepositories ("ru.aston.farmershop.repository")
@ComponentScan("ru.aston.farmershop")
public class SpringConfig implements WebMvcConfigurer {
    private final ApplicationContext context;

    public SpringConfig(ApplicationContext context) {
        this.context = context;
    }
}
