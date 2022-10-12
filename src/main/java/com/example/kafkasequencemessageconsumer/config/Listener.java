package com.example.kafkasequencemessageconsumer.config;

import com.example.kafkasequencemessageconsumer.model.Trade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Slf4j
@Configuration
@AllArgsConstructor
public class Listener {

    @Bean
    public Function<Flux<Message<Trade>>, Mono<Void>> contractEventsConsumer() {//NOSONAR
        return eventFlux -> eventFlux.flatMap(event -> {
                    var x =event.getHeaders();

                    log.info("Header data: {}", x);

                    var id = x.get("id");

                    log.info("event Id: {}", id);

                    var messageKey = x.get("kafka_receivedMessageKey");
                    log.info("MessageKey: {}", messageKey);

                    var contractRequest =  event.getPayload();//NOSONAR
                    log.info("Received event is: {}", contractRequest);
                    return Mono.just("app");
                }).then()
                .onErrorContinue(Exception.class, (exception, u) -> log.error("Consuming contract-event failed", exception));
    }
}
