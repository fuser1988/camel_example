
package com.somospnt.camel.router;

import com.somospnt.camel.domain.Feriado;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.context.annotation.Bean;


public class MyAggregationStrategy implements AggregationStrategy{
    
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null){
            return newExchange;
        }
        
        
        Feriado newBody = oldExchange.getIn().getBody(Feriado.class);
        Feriado[] oldBody = oldExchange.getIn().getBody(Feriado[].class);
        oldExchange.getOut().setBody(oldBody[oldBody.length - 1] = newBody);

        return oldExchange;
    }
}