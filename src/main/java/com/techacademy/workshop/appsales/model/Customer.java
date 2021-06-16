package com.techacademy.workshop.appsales.model;


import java.io.Serializable; 
 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{

    private long code;
    private String firstname;
    private String lastname;
    private String phone;
    private String points; 
    
}