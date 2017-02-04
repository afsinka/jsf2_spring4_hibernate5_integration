package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.aspect.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfig {

	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}
}
