package com.techacademy.workshop.appsales.proxy;

import com.techacademy.workshop.appsales.model.Customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import static com.techacademy.workshop.appsales.proxy.Utils.*;


@Component
public class CustomerProxy {

    @Value("${app.services.customer}")
    private String urlBase;

    public Mono<Customer> getCustomer(Long code) {

        return builder().baseUrl(urlBase).build().get()
                .uri(uri -> uri.path("/customer/{customer}")
                                .build(code)
                    )
                .exchangeToMono(bodyToMono(Customer.class));

    }
}
