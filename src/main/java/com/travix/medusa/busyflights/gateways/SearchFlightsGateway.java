package com.travix.medusa.busyflights.gateways;

import com.travix.medusa.busyflights.domain.AbstractSearchFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.http.HttpMethod.POST;

@Slf4j
public abstract class SearchFlightsGateway<T1, T2 extends AbstractSearchFlightsResponse> extends AbstractApiGateway {

    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {
        List<BusyFlightsResponse> searchResults = newArrayList();
        try {
            T1 generatedRequest = getRequest(request);
            log.info(String.format("Start calling service %s, request: %s",searchFlightsUri(),generatedRequest));
            searchResults = getRestTemplate()
                    .exchange(searchFlightsUri(), POST, new HttpEntity<>(getRequest(request)),getParameterizedTypeReference())
                    .getBody().stream().map(AbstractSearchFlightsResponse::buildResponse).collect(Collectors.toList());
        } catch (Exception e) {
            log.error(String.format("Error calling external service : %s", searchFlightsUri().toString()), e);
        }
        return searchResults;
    }

    protected abstract ParameterizedTypeReference<List<T2>> getParameterizedTypeReference();

    abstract URI searchFlightsUri();

    abstract T1 getRequest(BusyFlightsRequest request);
}
