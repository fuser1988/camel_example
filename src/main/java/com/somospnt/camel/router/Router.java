package com.somospnt.camel.router;

import com.somospnt.camel.domain.Feriado;
import com.somospnt.camel.service.FeriadoService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {

    @Autowired
    private FeriadoService feriadoService;

    @Override
    public void configure() throws Exception {

//        from("servlet:///feriados").to("direct:inicio");
//        rest("/feriados").consumes("application:json")
//                .post().description("se postean feriados")
//                .to("direct:pipeline");
//
//        from("direct:pipeline")
//                .unmarshal().json(JsonLibrary.Jackson, Feriado[].class)
//                .split(body()).pipeline("direct:inicio", "direct:otro");
//
//        from("direct:otro")
//                .log(body().toString());
//        from("direct:inicio")
//                .log("entro al inicio")
//                .choice()
//                .when().method(feriadoService, "esInamovible")
//                .to("direct:Inamovibles")
//                .otherwise()
//                .to("direct:NoInamovible");
//        
        from("timer://foo?fixedRate=true&delay=0&period=4000")
                .to("http://apiday.somospnt.com/api/feriados/2019")
                .unmarshal().json(JsonLibrary.Jackson, Feriado[].class)
                .split(body()).to("direct:otro");
//                .pipeline("direct:inicio", "direct:otro");
        
        from("direct:otro")
                .filter(message -> (message.getIn().getBody(Feriado.class).getTipo().equals("INAMOVIBLE")))
                .log(body().toString());
                
        
        
        from("direct:inicio")
                .log("entro al inicio")
                .choice()
                .when().method(feriadoService, "esInamovible")
                .to("direct:Inamovibles")
                .otherwise()
                .to("direct:NoInamovible");        


        
                
//                .split(body())
//                .filter(message -> (message.getIn().getBody(Feriado.class).getTipo().equals("INAMOVIBLE")))
//                
//                    
//                .choice()
//                .when().method(feriadoService, "esMesMayorA6")
//                .to("direct:Mayores")
//                .otherwise()
//                .to("direct:Menores");
                
               
        from("direct:Inamovibles")
                
                .log("paso por inamovibles".concat(body().toString()));
//                .transform().method(feriadoService, "inamovibles")
//                //                .to("direct:baseDatos")
//                .log(body().toString());

        from("direct:NoInamovible")
                .log("paso por Noinamovibles".concat(body().toString()));
//                .choice()
//                .when().method(feriadoService, "esMesMayorA6")
//                .to("direct:Mayores")
//                .otherwise()
//                .to("direct:Menores");

//        from("direct:Mayores")
//                .transform().method(feriadoService, "mesMayorA6")
//                //                .to("direct:baseDatos")
//                .log(body().toString());
//
//        from("direct:Menores")
//                .transform().method(feriadoService, "mesMenorA6")
//                //                .to("direct:baseDatos")
//                .log(body().toString());
//        from("direct:baseDatos")
//                .log("se guardaron")
//                .inOnly("jpa://com.somospnt.camel.domain.Feriado");
    }
}
