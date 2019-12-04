package com.somospnt.camel.router;

import com.somospnt.camel.domain.Feriado;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {
    
    JacksonXMLDataFormat format = new JacksonXMLDataFormat();
    
    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&delay=0&period=4000")
                .to("http://apiday.somospnt.com/api/feriados/2019")
                .unmarshal().json(JsonLibrary.Jackson, Feriado[].class)
                .pipeline("direct:otro","direct:inicio");
        
        from("direct:otro")
                .split(body())
                .filter(message -> (message.getIn().getBody(Feriado.class).getTipo().equals("INAMOVIBLE")))               
                .transform().simple("${body.toString()}")
                .to("file:C:/Dias no laborales/2019");
                
        
        from("direct:inicio")
                .marshal(format)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
                .to("http://demo0144334.mockable.io/Api/feriados")
                .log(body().toString());

        
    }
}
