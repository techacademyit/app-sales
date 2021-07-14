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
    private String firstname;
    private String lastname;
    private String phone;
    private Collection<Product> products;
}
