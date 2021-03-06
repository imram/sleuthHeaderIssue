/***
 * Copyright (c) 2017 American Airlines, Inc. All rights reserved
 */
package com.example.sleuthheaderdemo.sleuth;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanTextMap;
import org.springframework.cloud.sleuth.instrument.web.ZipkinHttpSpanInjector;

import static org.springframework.cloud.sleuth.Span.idToHex;

class CustomHttpServletResponseSpanInjector extends ZipkinHttpSpanInjector {

    @Override
    public void inject(Span span, SpanTextMap carrier) {
        carrier.put(Span.TRACE_ID_NAME, span.traceIdString());
        carrier.put(Span.SPAN_ID_NAME, idToHex(span.getSpanId()));
    }
}

