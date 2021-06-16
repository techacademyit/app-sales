package com.techacademy.workshop.appsales.web;

import com.techacademy.workshop.appsales.model.CustomerInfo;
import com.techacademy.workshop.appsales.services.CustomerInfoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@RestController
public class CustomerInfoRestController {

	private CustomerInfoService service;

    @GetMapping(value="/{code}")
	public Mono<CustomerInfo> getMethodName(@PathVariable("code") Long code) {
		return service.find(code);
	}
    
}
