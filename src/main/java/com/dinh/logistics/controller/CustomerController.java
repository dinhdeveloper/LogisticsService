package com.dinh.logistics.controller;

import com.dinh.logistics.exception.RecordNotFoundException;
import com.dinh.logistics.model.Customer;
import com.dinh.logistics.model.LoginUser;
import com.dinh.logistics.model.PushNotificationData;
import com.dinh.logistics.model.PushNotificationRequest;
import com.dinh.logistics.service.CustomerService;
import com.dinh.logistics.ultils.ResponseHandler;
import com.dinh.logistics.ultils.StatusResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/list")
    public ResponseEntity<Object> getAllProduct() {
        List<Customer> list = customerService.getAllCustomer();
        if (list != null){
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.SUCCESS, list);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.ERROR, null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginUser loginUser)
            throws RecordNotFoundException, FirebaseMessagingException {
        List<Customer> requestLogin = customerService.loginUser(loginUser.getUserName(),loginUser.getPassWord());
        if (requestLogin != null){

            PushNotificationRequest request = new PushNotificationRequest();
            request.setTo("cFO5r3rcSy6KXTpKBql7z5:APA91bHcOKL3G3-HSHQLTFkFe7lefTLx0QF57B4I_MOy-OwH4whjyTrHux9RktZqfPasmEdN7MQSBbCPpEhuDbnHSSk2KtJjFBaFQwoJZ_HkrOgULhvgOoylOdzdN9Vysdi7Y-Pv4X1z");
            request.setData(new PushNotificationData(
                    "Chúc bạn 1 ngày tốt lành",
                    "DETAIL",
                    "Chúc bạn 1 ngày tốt lành"
            ));

            try {
                Gson gson = new Gson();
                String jsonData = gson.toJson(request.getData());

                Message message = Message.builder()
                        .setToken(request.getTo())
                        .putData("data", jsonData)  // Add custom data
                        .build();

                String response = FirebaseMessaging.getInstance().send(message);
                ResponseEntity.ok("Notification sent successfully: " + response);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error sending notification: " + e.getMessage());
            }

            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.SUCCESS, requestLogin);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.ERROR, null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchCustomers(@RequestParam("keyword") String keyword) {
        List<Customer> reponse = customerService.searchCustomers(keyword);
        if (reponse != null){
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.SUCCESS, reponse);
        }else {
            return ResponseHandler.generateResponse(HttpStatus.OK, 0, StatusResult.ERROR, null);
        }
    }



}
