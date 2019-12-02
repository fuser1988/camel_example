package com.somospnt.camel;

import com.somospnt.camel.router.Router;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
public class CamelDemoApplication {

    private static final String CAMEL_URL_MAPPING = "/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    public static void main(String[] args) {
        SpringApplication.run(CamelDemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

    @Bean
    public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
        SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
        camelContext.addRoutes(routeBuilder());
        return camelContext;
    }

    @Bean
    public RouteBuilder routeBuilder() {
        return new Router();
    }
}
