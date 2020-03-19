package com.ecs.common;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 异常工具类
 * @author xuluyang
 *
 * 2020年3月18日
 */
//@ControllerAdvice
@Configuration
public class GlobaExceptionHandler {


	//注意包别导入错了
		@Bean
	    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
	        return factory -> {
	            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
	                    "/error/404");
	            System.out.println("404-------->>>>");
	            ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
	                    "/error/400");
	            System.out.println("400-------->>>>");
	            ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
	                    "/error/500");
	            System.out.println("500-------->>>>");
	            factory.addErrorPages(errorPage400, errorPage404,
	                    errorPage500);
	        };
	    }
}
