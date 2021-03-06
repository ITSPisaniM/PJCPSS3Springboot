package it.kennedy.cpss.springbootcpss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterCorsConfig implements WebMvcConfigurer {
	  
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer() 
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry
						
						.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("PUT", "DELETE", "GET", "POST","PATCH")
						.allowedHeaders("*")
						.exposedHeaders("newToken")
						.allowCredentials(false)
						.maxAge(3600); //3600 secondi --> 1h
			}
		};
	}
}
