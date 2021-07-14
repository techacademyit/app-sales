package com.techacademy.workshop.appsales.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{

    private Long code; 
    private String description;
    private String category;
    
}
