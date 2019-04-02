package com.app.sugarcrush.config.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
/*
 * This configurations should only be used during my test_db environment
 * */
@Profile("junit") 
/*
 * Load the property configuration file from test_mysql_config.properties present in classpath
 * */
@PropertySource(value="classpath:test_application.properties") 
@ComponentScan(basePackages= {"com.app.sugarcrush.repo","com.app.sugarcrush.model"})
public class ApplicationConfig {

	@Autowired
    Environment env;
	
	/*
	 * If a DataSource is configured manually then Spring boot will not create a DataSource and spring boot's 
	 *  DataSource comes with connection pooling and other function which will not be available for us , in-order to learning 
	 *  we are using this profile 
	 * */
	@Bean
	public DataSource dataSource() {
		    return DataSourceBuilder.create()
	        		.url(env.getProperty("spring.datasource.url"))
	        		.username(env.getProperty("spring.datasource.username"))
	        		.password(env.getProperty("spring.datasource.password"))
	        		.driverClassName(env.getProperty("spring.datasource.driverClassName"))
	        		.build();   
	}
	
	
	
	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(new String[] {"com.app.sugarcrush"});
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		return sessionFactoryBean;
	}
	
	@Bean 
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	
	
}
