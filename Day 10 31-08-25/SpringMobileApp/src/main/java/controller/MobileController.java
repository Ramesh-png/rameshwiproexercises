package controller;
import org.springframework.web.bind.annotation.*;

import dto.Mobile;

@RestController
public class MobileController {

    @PostMapping("/mobile")
    public String addMobile(@RequestBody Mobile mobile) {
        System.out.println("Received Mobile Data: " + mobile);
        return "Mobile data received successfully!";
    }
}
