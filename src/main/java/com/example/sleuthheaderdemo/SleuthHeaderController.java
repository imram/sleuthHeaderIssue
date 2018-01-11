package com.example.sleuthheaderdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SleuthHeaderController {
    public static final Logger LOGGER = LoggerFactory.getLogger(SleuthHeaderController.class);
    public static final String HTTP_LOCALHOST_URL = "http://localhost:9090/";
    public static final String HTTP_BLUEMIX_URL = "https://sleuth-header-demo.mybluemix.net/";

    @Value("${home.url}")
    String url;

    @Autowired
    private RestTemplate restTemplate;



    @NewSpan
    @GetMapping(value = "/hello")
    public String hello(@RequestParam(name = "name") String name,
            @RequestHeader(name = "X-B3-TraceId", required = false) String traceId) {

        LOGGER.info(String.format("%s Forwarding to Home", name));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("X-B3-TraceId", traceId);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response =  restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    @NewSpan
    @GetMapping(value = "/")
    public String home() {

        LOGGER.info("Call is at Home");
        return "Welcome There!!";
    }


}
