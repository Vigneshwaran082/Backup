package com.app.sugarcrush.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	
	
	@Bean
	@Autowired
	public SessionFactory sessionFactory(EntityManagerFactory entityManagerFactory) {
		 if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
		        throw new NullPointerException("factory is not a hibernate factory");
		    }
		return  entityManagerFactory.unwrap(SessionFactory.class);
	}
}


