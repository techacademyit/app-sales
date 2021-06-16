package com.techacademy.workshop.appsales.services.impl;

import com.techacademy.workshop.appsales.model.CustomerInfo;
import com.techacademy.workshop.appsales.proxy.ProductProxy;
import com.techacademy.workshop.appsales.services.CustomerInfoService;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    public ProductProxy proxy;
    
    public Mono<CustomerInfo> find(Long code){
       
        return proxy.getCustomer(code)
                .zipWith( proxy.getProduct(code))
                .map( f -> new CustomerInfo(f.getT1().getCode(), f.getT2().values()));
                    
    }
}
