package com.dinh.logistics.controller;


import com.dinh.logistics.exception.RecordNotFoundException;
import com.dinh.logistics.model.Customer;
import com.dinh.logistics.model.LoginUser;
import com.dinh.logistics.model.TaskCustomer;
import com.dinh.logistics.model.TaskCustomerRequest;
import com.dinh.logistics.service.CustomerService;
import com.dinh.logistics.service.TaskCustomerService;
import com.dinh.logistics.ultils.ResponseHandler;
import com.dinh.logistics.ultils.StatusResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/task")
public class TaskCustomerController {

    @Autowired
    TaskCustomerService taskCustomerService;


    @PostMapping("/taskByCustomer")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody TaskCustomerRequest taskCustomer)
            throws RecordNotFoundException, ParseException {
        List<TaskCustomer> requestLogin = taskCustomerService.getTaskByCustomer(taskCustomer);
        if (requestLogin != null){
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.SUCCESS, requestLogin);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.ERROR, null);
        }
    }

}
