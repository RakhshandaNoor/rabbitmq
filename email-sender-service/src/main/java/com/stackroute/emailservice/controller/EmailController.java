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
    public ResponseEntity<?> saveDefault(@RequestParam String email){

        emailService.sendMessage(email);
        System.out.println("sending message from controller+++"+ email );
        return new ResponseEntity<String>("message consoled", HttpStatus.OK);

    }

    @PostMapping("email/fanout")
    public ResponseEntity<?> saveFanout(@RequestParam String email){

        emailService.sendToFanout(email);
        System.out.println("sending message from controller+++"+ email );
        return new ResponseEntity<String>("message consoled", HttpStatus.OK);

    }

    @PostMapping("email/direct")
    public ResponseEntity<?> saveDirect(@RequestParam String email){

        emailService.sendToDirect(email);
        System.out.println("sending message from controller+++"+ email );
        return new ResponseEntity<String>("message consoled", HttpStatus.OK);

    }

    @PostMapping("email/topic")
    public ResponseEntity<?> saveTopic(@RequestParam String email){

        emailService.sendToTopic(email);
        System.out.println("sending message from controller+++"+ email );
        return new ResponseEntity<String>("message consoled", HttpStatus.OK);

    }
}
