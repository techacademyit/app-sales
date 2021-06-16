package com.techacademy.workshop.appsales.services;

import com.techacademy.workshop.appsales.model.CustomerInfo;

import reactor.core.publisher.Mono;

public interface CustomerInfoService {

    public Mono<CustomerInfo> find(Long code);
    
}
