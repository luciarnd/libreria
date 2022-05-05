package com.example.Ejercicio.back.resource;

import com.example.Ejercicio.back.model.Mail;
import com.example.Ejercicio.back.services.MailServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Component
@RestController
@RequestMapping("/")
@NoArgsConstructor
@AllArgsConstructor
public class MailResource {

    @Autowired
    private MailServices mailServices;


    @PostMapping("enviarmail")
    public ResponseEntity<Mail> enviarMail(@RequestBody Mail mail){
        mailServices.sendMail(mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}