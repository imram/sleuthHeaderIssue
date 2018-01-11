/***
 * Copyright (c) 2017 American Airlines, Inc. All rights reserved
 */
package com.example.sleuthheaderdemo.sleuth;

import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.HttpSpanInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {
    @Bean
    HttpSpanInjector customHttpServletResponseSpanInjector() {
        return new CustomHttpServletResponseSpanInjector();
    }

    @Bean
    HttpResponseInjectingTraceFilter responseInjectingTraceFilter(Tracer tracer) {
        return new HttpResponseInjectingTraceFilter(tracer, customHttpServletResponseSpanInjector());
    }
}
