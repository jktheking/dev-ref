package edu.jktheking.misc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaMiscApplication {

	public static void main(String[] args) {
	ApplicationContext ctxt = 	SpringApplication.run(JavaMiscApplication.class, args);
		
	System.out.println("from inside the parent app: "+ctxt.getEnvironment().getProperty("child.configProfiles"));	
	
		
	}

	

	
}
