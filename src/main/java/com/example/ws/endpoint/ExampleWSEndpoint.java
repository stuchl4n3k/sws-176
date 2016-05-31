package com.example.ws.endpoint;

import com.example.ws.example.ExampleRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ExampleWSEndpoint {

    public static final String NAMESPACE_URI = "http://example.com/ws/example";

    @PayloadRoot(localPart = "ExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public void exampleOperation(@RequestPayload final ExampleRequest request) {
        System.err.println("RX: " + request);
    }


}
