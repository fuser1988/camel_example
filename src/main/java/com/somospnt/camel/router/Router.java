package com.somospnt.camel.router;

import com.somospnt.camel.domain.Feriado;
import com.somospnt.camel.domain.Response;
import com.somospnt.camel.service.FeriadoService;
import org.apache.camel.Exchange;
import org.apache.camel.InOnly;
import org.apache.camel.Processor;
import org.apache.camel.builder.ProcessClause;
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
        rest("/feriados").consumes("application:json")
                .post().description("se postean feriados")
                .to("direct:pipeline");

        from("direct:pipeline")
                .unmarshal().json(JsonLibrary.Jackson, Feriado[].class)
                .split(body()).pipeline("direct:inicio", "direct:otro");

        from("direct:otro")
                .log(body().toString());
        from("direct:inicio")
                .log("entro al inicio")
                .choice()
                .when().method(feriadoService, "esInamovible")
                .to("direct:Inamovibles")
                .otherwise()
                .to("direct:NoInamovible");
        
//        from("timer://foo?fixedRate=true&delay=0&period=4000")
//                .to("http://apiday.somospnt.com/api/feriados/2016")
//                .unmarshal().json(JsonLibrary.Jackson, Feriado[].class)
//                .log("unmarshal: || " + body() + " || ")
//                .split().body()
//                .log("spliteado || " + body() + " || ")
//                .choice()
//                .when().method(feriadoService, "esInamovible")
//                .to("direct:Inamovibles")
//                .otherwise()
//                .to("direct:NoInamovible");

        from("direct:Inamovibles")
                .transform().method(feriadoService, "inamovibles")
                //                .to("direct:baseDatos")
                .log(body().toString());

        from("direct:NoInamovible")
                .choice()
                .when().method(feriadoService, "esMesMayorA6")
                .to("direct:Mayores")
                .otherwise()
                .to("direct:Menores");

        from("direct:Mayores")
                .transform().method(feriadoService, "mesMayorA6")
                //                .to("direct:baseDatos")
                .log(body().toString());

        from("direct:Menores")
                .transform().method(feriadoService, "mesMenorA6")
                //                .to("direct:baseDatos")
                .log(body().toString());

        from("direct:baseDatos")
                .log("se guardaron")
                .inOnly("jpa://com.somospnt.camel.domain.Feriado");

    }
}
