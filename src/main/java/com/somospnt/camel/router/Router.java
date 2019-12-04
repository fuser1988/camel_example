package com.somospnt.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&delay=0&period=4000")
            .pipeline("direct:log","direct:post");
        
        from("direct:log")
            .log("#### paso por log ####");
                
        from("direct:post")
            .log("#### paso por post ####");

        
    }
}
