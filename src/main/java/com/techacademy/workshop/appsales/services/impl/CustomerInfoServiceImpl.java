package com.techacademy.workshop.appsales.services.impl;

import com.techacademy.workshop.appsales.model.CustomerInfo;
import com.techacademy.workshop.appsales.proxy.CustomerProxy;
import com.techacademy.workshop.appsales.proxy.ProductProxy;
import com.techacademy.workshop.appsales.services.CustomerInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    public ProductProxy pProxy;
    
    @Autowired
    public CustomerProxy cProxy;
    
    public Mono<CustomerInfo> find(Long code){
       
        return cProxy.getCustomer(code)
                .zipWith( pProxy.getProduct(code))
                .map( f -> new CustomerInfo(f.getT1().getCode(), 
                                            f.getT1().getFirstname(),
                                            f.getT1().getLastname(),
                                            f.getT1().getPhone(),
                                            f.getT2().values()));
                    
    }
}
