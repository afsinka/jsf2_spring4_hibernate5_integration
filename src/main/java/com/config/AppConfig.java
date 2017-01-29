package com.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.model.Book;

@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:db.properties") })
@Configuration
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {

		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
		driverManagerDataSource.setUsername(env.getProperty("jdbc.username"));
		driverManagerDataSource.setPassword(env.getProperty("jdbc.password"));
		return driverManagerDataSource;

	}

	@Bean
	public LocalSessionFactoryBean customSessionFactory() {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource());
		sf.setAnnotatedClasses(Book.class);
		sf.setHibernateProperties(hibernateProperties());
		return sf;

	}

	@Bean
	public Properties hibernateProperties() {
		Properties p = new Properties();
		p.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		p.put("hibernate.show_sql", false);
		p.put("hibernate.hbm2ddl.auto", "update");
		return p;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(customSessionFactory().getObject());
		return htm;
	}

}
