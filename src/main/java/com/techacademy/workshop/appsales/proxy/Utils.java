package com.techacademy.workshop.appsales.proxy;
 
import org.springframework.http.client.reactive.ReactorClientHttpConnector; 
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
 
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration; 
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import reactor.netty.http.client.HttpClient;

public final class Utils {

    public static <V>  Function<ClientResponse, ? extends Flux<V>> bodyToFlux(Class<V> clazz){
        return (response) -> {
            if (response.statusCode().is2xxSuccessful()) {
                return  response.bodyToFlux(clazz);
            } else if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
                return response.createException()
                               .flatMapMany( f -> Flux.error(f))
                               .cast(clazz);
            }else {
                return Flux.error(new RuntimeException());
            }
        };
    }

    public static <V>  Function<ClientResponse, ? extends Mono<V>> bodyToMono(Class<V> clazz){
        return (response) -> {
            if (response.statusCode().is2xxSuccessful()) {
                return  response.bodyToMono(clazz);
            } else if (response.statusCode().is4xxClientError() || response.statusCode().is5xxServerError()) {
                return response.createException().flatMap( e -> Mono.error(e));
            }else {
                return Mono.error(new RuntimeException());
            }
        };
    }

    public static WebClient.Builder builder(){        
        return WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector( httpClient()));
             
    }

    public static HttpClient httpClient(){
        return HttpClient.create()
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                        .responseTimeout(Duration.ofMillis(5000))
                        .doOnConnected(conn -> 
                            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        );
    }
    
}
