package it.kennedy.cpss.springbootcpss;

import java.util.concurrent.Executor;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
public class SpringCpssApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCpssApplication.class, args);
		Logger logger = Logger.getLogger(SpringCpssApplication.class.getName());
		logger.info("------------------------------");
		logger.info("\tAPPLICATION STARTED UP");
		logger.info("------------------------------");
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("getOrdini-");
		executor.initialize();
		return executor;
	}
}
