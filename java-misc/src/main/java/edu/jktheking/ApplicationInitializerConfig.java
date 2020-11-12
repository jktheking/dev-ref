package edu.jktheking;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationInitializerConfig {
	
	
	@Autowired
	private Environment env;
	
	
	@PostConstruct
	void postConstruct() {
		
		System.out.println("printing from config app:"+env.getProperty("borrowed.property"));
	}
	
	
	@PreDestroy
	void preDestroy() {
		
		System.out.println("shuting down the  config app....");
		
	}
	
	@Bean(name="configProfiles")
	Set<String> configProfiles(){
		return Set.of("configprofile1", "dev environment");
	}
	

}
