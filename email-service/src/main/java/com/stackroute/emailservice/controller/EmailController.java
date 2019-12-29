package com.stackroute.emailservice.controller;


import com.stackroute.emailservice.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @PostMapping("email")
    public ResponseEntity<?> saveQuiz(@RequestParam String email){

        emailService.sendMessage(email);
        System.out.println("sending message from controller+++"+ email );
        return new ResponseEntity<String>("message consoled", HttpStatus.OK);

    }
}
