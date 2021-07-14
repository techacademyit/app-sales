package com.techacademy.workshop.appsales.proxy;
 

import com.techacademy.workshop.appsales.model.Product;
import com.techacademy.workshop.appsales.model.ProductByCustomer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component; 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map; 
import static com.techacademy.workshop.appsales.proxy.Utils.*;

@Component
public class ProductProxy {

    @Value("${app.services.product}")
    private String urlBase; 


    public Mono<Map<Long,Product>> getProduct(Long code){
    
        return  getProductsByCustomer(code)
                      .flatMap(  c -> getProductByCode(c.getCode())) 
                      .collectMap( f -> f.getCode())
                      .switchIfEmpty(Mono.just(new HashMap<>()));
    }

    public Flux<Product> getProduct(){
       
        return builder()
                    .baseUrl(urlBase)
                    .build()
                    .get()
                    .uri("/product")
                    .exchangeToFlux(bodyToFlux(Product.class));
                    
    }

    public Flux<ProductByCustomer> getProductsByCustomer(Long customer){
       
        return builder()
                    .baseUrl(urlBase)
                    .build()
                    .get()
                    .uri( uri -> uri.path("/product/by-customer/{customer}")
                                    .build(customer)
                    )
                    .exchangeToFlux( bodyToFlux(ProductByCustomer.class));
                    
    }

    public Mono<Product> getProductByCode(Long code){
       
        return builder()
                    .baseUrl(urlBase)
                    .build()
                    .get()
                    .uri( uri -> uri.path("/product/{customer}")
                                    .build(code)
                    )
                    .exchangeToMono( bodyToMono(Product.class));
                    
    }
    
    
}
