package com.techacademy.workshop.appsales;
 
import com.techacademy.workshop.appsales.proxy.ProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppSalesApplication {

	@Autowired
	ProductProxy proxy;

	public static void main(String[] args) {
		SpringApplication.run(AppSalesApplication.class, args);
	}
}
