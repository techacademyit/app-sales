package com.techacademy.workshop.appsales.model;

import java.io.Serializable;
import java.util.Collection; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo implements Serializable {
    
    private Long code;
    private Collection<Product> products;
}
