package com.travix.medusa.busyflights.gateways;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

abstract class AbstractApiGateway {

    private RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate(){
        return restTemplate;
    }

    abstract String getHost();

    abstract int getPort();

    URI endpoint(String endpoint){
        return URI.create(String.format("%s:%s/%s",getHost(),getPort(),endpoint));
    }
}
