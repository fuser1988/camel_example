package com.somospnt.camel;

import com.somospnt.camel.domain.Feriado;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CamelDemoApplication {

    @Component
    class CargaFeriados extends RouteBuilder {

        @Override
        public void configure() throws Exception {
//            http://camel.apache.org/
            from("")
//                    // Consumir apiday (http://apiday.somospnt.com/api/feriados/2016)
//                    // unmarshal de json a feriados
//                    // split del body
//                    // filter por tipo (INAMOVIBLE, NO LABORABLES, PUENTE, TRASLADABLE) con javascript
//                    // transform para agregar nombre del equipo con groovy
//                    // jpa insert de feriado
                    .log(body().toString());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CamelDemoApplication.class, args);
    }

}
