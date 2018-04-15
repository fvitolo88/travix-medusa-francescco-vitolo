package com.travix.medusa.busyflights.builders;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SearchFlightsRequestBuilderTest {

    private BusyFlightsRequest busyFlightsRequest;

    @Before
    public void init(){
        String origin = "Origin";
        LocalDateTime departureDate = LocalDateTime.of(2018, Month.APRIL,15,20,30);
        String destination = "Destination";
        LocalDateTime returnDate = LocalDateTime.of(2018, Month.APRIL,20,20,30);
        int numberOfPassengers = 10;

        busyFlightsRequest =
                new BusyFlightsRequest()
                        .withOrigin(origin)
                        .withDepartureDate(departureDate)
                        .withDestination(destination)
                        .withReturnDate(returnDate)
                        .withNumberOfPassengers(numberOfPassengers);
    }

    @Test
    public void shouldCreateCrazyAirRequestFromBusyFlightsRequest(){
        CrazyAirRequest crazyAirRequest = new SearchFlightsRequestBuilder(busyFlightsRequest).buildCrazyAirRequest();

        assertEquals(crazyAirRequest.getDepartureDate(),busyFlightsRequest.getDepartureDate());
        assertEquals(crazyAirRequest.getDestination(),busyFlightsRequest.getDestination());
        assertEquals(crazyAirRequest.getOrigin(),busyFlightsRequest.getOrigin());
        assertEquals(crazyAirRequest.getReturnDate(),busyFlightsRequest.getReturnDate());
        assertEquals(crazyAirRequest.getPassengerCount(),busyFlightsRequest.getNumberOfPassengers());
    }

    @Test
    public void shouldCreateToughJetRequestFromBusyFlightsRequest(){

        ToughJetRequest toughJetRequest = new SearchFlightsRequestBuilder(busyFlightsRequest).buildToughJetRequest();

        assertEquals(toughJetRequest.getOutboundDate(),busyFlightsRequest.getDepartureDate());
        assertEquals(toughJetRequest.getTo(),busyFlightsRequest.getDestination());
        assertEquals(toughJetRequest.getFrom(),busyFlightsRequest.getOrigin());
        assertEquals(toughJetRequest.getInboundDate(),busyFlightsRequest.getReturnDate());
        assertEquals(toughJetRequest.getNumberOfAdults(),0);
    }
}
