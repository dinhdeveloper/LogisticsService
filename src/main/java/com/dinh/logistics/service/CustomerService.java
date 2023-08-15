package com.dinh.logistics.service;

import com.dinh.logistics.model.Customer;
import com.dinh.logistics.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

//    public ResponseEntity<Object> getAllCustomer() {
//        return ResponseHandler.generateResponse(HttpStatus.OK, 0, "success",
//                repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
//    }

    //get all
    public List<Customer> getAllCustomer() {
        List<Customer> customers = repository.findAll();
        if (customers.size() > 0) {
            return customers;
        } else {
            return new ArrayList<Customer>();
        }
    }


    public List<Customer> loginUser(String userName, String passWord) {
        return repository.findByUsernameAndPassword(userName, passWord);
    }

    public List<Customer> searchCustomers(String search){
        List<Customer> customer = repository.searchCustomers(search);
        if (customer != null){
            return customer;
        }else {
            return null;
        }
    }
}
