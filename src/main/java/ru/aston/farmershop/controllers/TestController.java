package ru.aston.farmershop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping(value = "/healthCheck")
  private ResponseEntity<String> healthCheck(){
    return new ResponseEntity<>("Health is ok!", HttpStatus.OK);
  }

}
