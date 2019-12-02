package com.somospnt.camel.endpoint.impl;

import com.somospnt.camel.domain.Feriado;
import com.somospnt.camel.domain.Response;
import com.somospnt.camel.endpoint.FeriadoEndPoint;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Component
public class FeriadoEndPointImpl implements FeriadoEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(FeriadoEndPointImpl.class);
    @Produce(uri = "direct:inicio")
    private ProducerTemplate producerTemplate;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HttpHeaders httpHeaders;

    @Override
    public void crearEndPoint(List<Feriado> feriados) {
        producerTemplate.sendBody(feriados);
    }

//    @Scheduled(fixedDelay = 10000)
    public void enviarFeriados() throws IOException {
        UriComponentsBuilder uriComponentFeriados = UriComponentsBuilder.fromHttpUrl("http://localhost:8090/feriados");
        URI uriFeriados = uriComponentFeriados.build().toUri();
        String feriados = IOUtils.toString(
                new FileInputStream("src/main/resources/feriados.json"), "UTF-8");

        restTemplate.exchange(uriFeriados, HttpMethod.POST, new HttpEntity(feriados, httpHeaders), Feriado[].class);
//        logger.info(response.toString());
    }
}
