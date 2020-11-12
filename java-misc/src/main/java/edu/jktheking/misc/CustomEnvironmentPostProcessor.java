package edu.jktheking.misc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import edu.jktheking.ApplicationInitializerConfig;

public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {
	
	

	
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		
		
		//adding property into child project
		ApplicationContextInitializer<ConfigurableApplicationContext> initializer = (context) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("borrowed.property", "this is working.");
			
			context.getEnvironment().getPropertySources().addLast(new MapPropertySource("child-custom-source", map));
		};
		
		AnnotationConfigApplicationContext ctxt = new AnnotationConfigApplicationContext();
		ctxt.register(ApplicationInitializerConfig.class);
		ctxt.registerShutdownHook();
		initializer.initialize(ctxt);
		ctxt.refresh();
		
		
		Object configProfiles = ctxt.getBean("configProfiles");
		
		
		environment.getPropertySources().addLast(new MapPropertySource("parent-custom-source", Map.of("child.configProfiles", configProfiles)));
		
		
		
		
	}

}
