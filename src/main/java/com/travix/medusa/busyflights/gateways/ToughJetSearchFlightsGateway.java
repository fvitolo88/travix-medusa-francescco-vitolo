package com.travix.medusa.busyflights.gateways;

import com.travix.medusa.busyflights.builders.SearchFlightsRequestBuilder;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
public class ToughJetSearchFlightsGateway extends SearchFlightsGateway<ToughJetRequest, ToughJetResponse> {

    private static final String SEARCH_FLIGHTS_ENDPOINT = "api/search";

    @Override
    String getHost() {
        return "http://toughJet";
    }

    @Override
    int getPort() {
        return 8080;
    }

    @Override
    protected ParameterizedTypeReference<List<ToughJetResponse>> getParameterizedTypeReference() {
        return new ParameterizedTypeReference<List<ToughJetResponse>>() {};
    }

    @Override
    URI searchFlightsUri() {
        return endpoint(SEARCH_FLIGHTS_ENDPOINT);
    }

    @Override
    ToughJetRequest getRequest(BusyFlightsRequest request) {
        return new SearchFlightsRequestBuilder(request).buildToughJetRequest();
    }
}
