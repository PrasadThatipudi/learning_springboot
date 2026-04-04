package org.learning.irctc.controller;

import org.learning.annotation.GetMapping;
import org.learning.annotation.RestController;

@RestController("/test")
public class GreetingController {
    @GetMapping("/greet")
    public String greet() {
        return "Hello, welcome to IRCTC!";
    }
}
