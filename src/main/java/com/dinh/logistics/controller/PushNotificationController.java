package com.dinh.logistics.controller;

import com.dinh.logistics.model.PushNotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PushNotificationController {

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendPushNotification(@RequestBody PushNotificationRequest request) {
        try {
            Gson gson = new Gson();
            String jsonData = gson.toJson(request.getData());

            Message message = Message.builder()
                    .setToken(request.getTo())
                    .putData("data", jsonData)  // Add custom data
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            return ResponseEntity.ok("Notification sent successfully: " + response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending notification: " + e.getMessage());
        }
    }

}
