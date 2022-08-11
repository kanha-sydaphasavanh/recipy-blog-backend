package fr.web.recipy;

import fr.web.recipy.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RecipyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipyApplication.class, args);
    }

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Bean
    public WebMvcConfigurer mvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/swagger-ui/index.html").allowedMethods("POST", "PUT").allowedOrigins("*");
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*", "GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(false).maxAge(3600);
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(tokenInterceptor);
            }
        };

    }
}
