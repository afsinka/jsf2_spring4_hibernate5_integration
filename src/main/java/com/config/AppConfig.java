package com.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@PropertySources({ @PropertySource("classpath:db.properties") })
@ComponentScan(basePackages = {"com"}) 
@EnableJpaRepositories(basePackages = "com.repository") 
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.model");
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(getJpaPropertyMap());
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws PropertyVetoException {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	private Map<String, Object> getJpaPropertyMap() {
		final Map<String, Object> jpaPropertyMap = new HashMap<>();
		jpaPropertyMap.put("hibernate.hbm2ddl.auto", "update");
		jpaPropertyMap.put("hibernate.show_sql", true);
		jpaPropertyMap.put("hibernate.format_sql", true);
		jpaPropertyMap.put("hibernate.generate_statistics", false);
		jpaPropertyMap.put("hibernate.max_fetch_depth", 0);
		jpaPropertyMap.put("hibernate.default_batch_fetch_size", "10");
		jpaPropertyMap.put("hibernate.jdbc.fetch_size", "10");
		// jpaPropertyMap.put("hibernate.jdbc.factory_class", "");
		jpaPropertyMap.put("hibernate.cache.use_query_cache", "false");
		jpaPropertyMap.put("hibernate.cache.use_second_level_cache", "false");
		// jpaPropertyMap.put(AvailableSettings.INTERCEPTOR, "");
		jpaPropertyMap.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return jpaPropertyMap;
	}
}
