//package com.somospnt.camel.controller;
//
//import com.somospnt.camel.domain.Feriado;
//import com.somospnt.camel.endpoint.FeriadoEndPoint;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FeriadoRest {
//    
//    @Autowired
//    private FeriadoEndPoint feriadoEndPoint;
//    
//    @PostMapping("/feriados")
//    public void obtener(@RequestBody List<Feriado> feriados) {
//        feriadoEndPoint.crearEndPoint(feriados);
//    }
//}
