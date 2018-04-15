package com.travix.medusa.busyflights.gateways;

import com.travix.medusa.busyflights.builders.SearchFlightsRequestBuilder;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
public class CrazyAirSearchFlightsGateway extends SearchFlightsGateway<CrazyAirRequest, CrazyAirResponse> {

    private static final String SEARCH_FLIGHTS_ENDPOINT = "api/search";

    @Override
    String getHost() {
        return "http://crazyAir";
    }

    @Override
    int getPort() {
        return 8080;
    }

    @Override
    protected ParameterizedTypeReference<List<CrazyAirResponse>> getParameterizedTypeReference() {
        return new ParameterizedTypeReference<List<CrazyAirResponse>>() {};
    }

    @Override
    URI searchFlightsUri() {
        return endpoint(SEARCH_FLIGHTS_ENDPOINT);
    }

    @Override
    CrazyAirRequest getRequest(BusyFlightsRequest request) {
        return new SearchFlightsRequestBuilder(request).buildCrazyAirRequest();
    }
}
