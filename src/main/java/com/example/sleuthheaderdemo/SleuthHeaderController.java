package com.example.sleuthheaderdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleuthHeaderController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SleuthHeaderController.class);

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(name = "name") String name,
            @RequestHeader(name = "X-B3-TraceId", required = false) String traceId) {

        LOGGER.info("Call Received");
        return "HELLO" + name;
    }
}
