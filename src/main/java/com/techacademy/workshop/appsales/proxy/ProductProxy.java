package com.techacademy.workshop.appsales.proxy;
 
import com.techacademy.workshop.appsales.model.Customer; 
import com.techacademy.workshop.appsales.model.Product;
import com.techacademy.workshop.appsales.model.ProductByCustomer;

import org.springframework.stereotype.Component; 
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
import java.util.HashMap;
import java.util.Map; 
import static com.techacademy.workshop.appsales.proxy.Utils.*;

@Component
public class ProductProxy {

     
    public Mono<Map<Long,Product>> getProduct(Long code){
        return getProductsByCustomer(code)
                .map( f -> new Product(f.getCode()))
                .collectMap( f -> f.getCode())
                .switchIfEmpty(Mono.just(new HashMap<>()));
    }

    public Mono<Customer> getCustomer(Long code){
       
        return builder()
                    .baseUrl("http://localhost:8082")
                    .build()
                    .get()
                    .uri( uri -> uri.path("/customer/{customer}")
                                    .build(code)
                    )
                    .exchangeToMono(bodyToMono(Customer.class));
                    
    }

    public Flux<Product> getProduct(){
       
        return builder()
                    .baseUrl("http://localhost:8081")
                    .build()
                    .get()
                    .uri("/product")
                    .exchangeToFlux(bodyToFlux(Product.class));
                    
    }

    public Flux<ProductByCustomer> getProductsByCustomer(Long customer){
       
        return builder()
                    .baseUrl("http://localhost:8081")
                    .build()
                    .get()
                    .uri( uri -> uri.path("/product/by-customer/{customer}?delay=1000")
                                    .build(customer)
                    )
                    .exchangeToFlux( bodyToFlux(ProductByCustomer.class));
                    
    }

    
      
    
}
