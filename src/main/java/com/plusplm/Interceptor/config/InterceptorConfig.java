package com.plusplm.Interceptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.plusplm.Interceptor.component.HttpInterceptor;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	HttpInterceptor httpInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(httpInterceptor)
					.addPathPatterns("/api/v1/**")
						.excludePathPatterns("/api/v1/comm/*");
	}
}
