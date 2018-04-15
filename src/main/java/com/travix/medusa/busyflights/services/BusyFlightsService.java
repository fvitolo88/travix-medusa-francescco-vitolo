package com.travix.medusa.busyflights.services;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.gateways.CrazyAirSearchFlightsGateway;
import com.travix.medusa.busyflights.gateways.ToughJetSearchFlightsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class BusyFlightsService {

    @Autowired
    private CrazyAirSearchFlightsGateway crazyAirSearchFlightsGateway;

    @Autowired
    private ToughJetSearchFlightsGateway toughJetSearchFlightsGateway;

    private final List<Function<BusyFlightsRequest,List<BusyFlightsResponse>>> providers =
            newArrayList(
                    (request) -> crazyAirSearchFlightsGateway.searchFlights(request),
                    (request) -> toughJetSearchFlightsGateway.searchFlights(request)
            );

    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request){
        List<BusyFlightsResponse> searchFlightsResult = new ArrayList<>();
        providers.forEach(searchFlightsProvider -> searchFlightsResult.addAll(searchFlightsProvider.apply(request)));
        return searchFlightsResult.stream().sorted(Comparator.comparing(BusyFlightsResponse::getFare)).collect(Collectors.toList());

    }
}
