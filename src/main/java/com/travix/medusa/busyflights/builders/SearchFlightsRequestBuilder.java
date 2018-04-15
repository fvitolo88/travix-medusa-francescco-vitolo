package com.travix.medusa.busyflights.builders;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchFlightsRequestBuilder {

    private BusyFlightsRequest request;

    public CrazyAirRequest buildCrazyAirRequest() {
        return new CrazyAirRequest()
                .withOrigin(request.getOrigin())
                .withDepartureDate(request.getDepartureDate())
                .withDestination(request.getDestination())
                .withReturnDate(request.getReturnDate())
                .withPassengerCount(request.getNumberOfPassengers());
    }

    public ToughJetRequest buildToughJetRequest() {
        return new ToughJetRequest()
                .withFrom(request.getOrigin())
                .withInboundDate(request.getReturnDate())
                .withOutboundDate(request.getDepartureDate())
                .withTo(request.getDestination());
    }
}
