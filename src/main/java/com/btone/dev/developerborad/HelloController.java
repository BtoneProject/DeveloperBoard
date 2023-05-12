package com.btone.dev.developerborad;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, w12312323orld!";
    }
}
