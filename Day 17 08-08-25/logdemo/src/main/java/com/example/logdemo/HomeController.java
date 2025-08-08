package com.example.logdemo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/greet")
    public String greet() {
        logger.error("--ERROR-- log");
        logger.warn("--WARN-- log");
        logger.info("--INFO-- log");
        logger.debug("--DEBUG-- log");
        logger.trace("--TRACE-- log");
        return "Check the logs folder for output!";
    }
}
