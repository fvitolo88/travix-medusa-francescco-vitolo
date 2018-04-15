package com.travix.medusa.busyflights.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.junit.Test;

import java.io.IOException;
import java.time.temporal.ChronoField;

import static junit.framework.TestCase.assertEquals;

public class DateDeserializationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldDeserializeIsoDateTime() throws IOException {
        String json = "{\"departureDate\":\"2018-04-15T20:04:05.867\",\"arrivalDate\":\"2018-04-15T20:04:05.867\"}";
        BusyFlightsResponse busyFlightsResponse = objectMapper.readValue(json,BusyFlightsResponse.class);
        assertEquals(busyFlightsResponse.getDepartureDate().get(ChronoField.YEAR),2018);
        assertEquals(busyFlightsResponse.getDepartureDate().get(ChronoField.MONTH_OF_YEAR),04);
        assertEquals(busyFlightsResponse.getDepartureDate().get(ChronoField.DAY_OF_MONTH),15);
    }

}
