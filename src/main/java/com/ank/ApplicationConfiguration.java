package com.ank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public MapperFactory mapperFactory() {
		
		 return new DefaultMapperFactory.Builder().build();
	}

}
