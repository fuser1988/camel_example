package com.somospnt.camel.router;

import com.somospnt.camel.CamelDemoApplicationTests;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class RouterTest extends CamelDemoApplicationTests{
    
//    @EndpointInject(uri = "mock:otro")
//    protected MockEndpoint resultEndpoint;
//    
//    @Produce(uri = "direct:pipeline")
//    protected ProducerTemplate template;
//    
//    @Test
//    public void mandarFeriados_enJson_seGuardaEnBase() throws IOException, InterruptedException {
//        String feriados = IOUtils.toString(new FileInputStream("src/test/resources/feriados.json"), "UTF-8");
//        resultEndpoint.expectedBodyReceived();
//        template.sendBody(feriados);
//        resultEndpoint.assertIsSatisfied();
//        
//        
//    }
}
