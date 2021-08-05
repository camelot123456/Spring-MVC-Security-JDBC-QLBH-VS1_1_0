package com.springtutorials.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springtutorials.interceptors.LoadNameUserInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//	private static final Charset UTF8 = Charset.forName("UTF-8");

	// Cấu hình UTF-8 cho các trang.
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		// TODO Auto-generated method stub
//		super.configureMessageConverters(converters);
//		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
//		stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "application/json", UTF8)));
//		converters.add(stringConverter);
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		super.addResourceHandlers(registry);
		// Css resource.
		registry.addResourceHandler("/template/**") //
				.addResourceLocations("/template/").setCachePeriod(31556926);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		super.addInterceptors(registry);
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}

}
