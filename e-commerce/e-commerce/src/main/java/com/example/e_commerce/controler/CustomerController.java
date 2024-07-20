package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Customers;
import com.example.e_commerce.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
   /* private  final CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<Customers> addCustomer( @Valid @RequestBody Customers customer){
        return ResponseEntity.status(HttpStatus.CREATED).body( customerService.addCustomer(customer)) ;

    }*/


}
