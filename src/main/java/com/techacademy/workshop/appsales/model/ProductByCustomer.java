package com.techacademy.workshop.appsales.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductByCustomer implements Serializable {

    private Long customer;
    private Long code;
    
}
