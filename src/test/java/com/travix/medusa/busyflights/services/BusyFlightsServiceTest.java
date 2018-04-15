package com.travix.medusa.busyflights.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.gateways.CrazyAirSearchFlightsGateway;
import com.travix.medusa.busyflights.gateways.ToughJetSearchFlightsGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({CrazyAirSearchFlightsGateway.class, ToughJetSearchFlightsGateway.class})
public class BusyFlightsServiceTest {

    @TestConfiguration
    public static class BusyFlightsServiceTestContextConfiguration {

        @Bean
        public BusyFlightsService busyFlightsService() {
            return new BusyFlightsService();
        }

        @Bean
        public CrazyAirSearchFlightsGateway crazyAirSearchFlightsGateway() {
            return new CrazyAirSearchFlightsGateway();
        }

        @Bean
        public ToughJetSearchFlightsGateway toughJetSearchFlightsGateway() {
            return new ToughJetSearchFlightsGateway();
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

    }

    @Autowired
    private BusyFlightsService busyFlightsService;

    @Autowired
    private CrazyAirSearchFlightsGateway crazyAirSearchFlightsGateway;

    @Autowired
    private ToughJetSearchFlightsGateway toughJetSearchFlightsGateway;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        String crazyAirResponse =
                objectMapper.writeValueAsString(
                        newArrayList(
                                new CrazyAirResponse()
                                        .withAirline("airline1")
                                        .withPrice(100.00)
                                        .withDepartureDate(LocalDateTime.of(2018, Month.APRIL, 15, 20, 30))
                                        .withArrivalDate(LocalDateTime.of(2018, Month.APRIL, 20, 20, 30))
                                        .withCabinclass("E")
                                        .withDepartureAirportCode("LHR")
                                        .withDestinationAirportCode("AAA")
                        )
                );

        String toughJetResponse =
                objectMapper.writeValueAsString(
                        newArrayList(
                                new ToughJetResponse()
                                        .withCarrier("airline2")
                                        .withBasePrice(100.555)
                                        .withDiscount(10)
                                        .withTax(3)
                                        .withDepartureAirportName("LHR")
                                        .withArrivalAirportName("AAA")
                                        .withOutboundDateTime(LocalDateTime.of(2018, Month.APRIL, 15, 20, 30))
                                        .withInboundDateTime(LocalDateTime.of(2018, Month.APRIL, 20, 20, 30))
                        ));

        server.bindTo(crazyAirSearchFlightsGateway.getRestTemplate()).build().expect(requestTo("http://crazyAir:8080/api/search"))
                .andRespond(withSuccess(crazyAirResponse, MediaType.APPLICATION_JSON));

        server.bindTo(toughJetSearchFlightsGateway.getRestTemplate()).build().expect(requestTo("http://toughJet:8080/api/search"))
                .andRespond(withSuccess(toughJetResponse, MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldReturnOrderedByFareFlightsList() {
        List<BusyFlightsResponse> results = busyFlightsService.searchFlights(new BusyFlightsRequest());

        assertTrue(results.size() == 2);

        BusyFlightsResponse firstResult = results.get(0);
        BusyFlightsResponse secondResult = results.get(1);

        assertEquals(firstResult.getAirline(), "airline2");
        assertTrue(firstResult.getDepartureDate().compareTo(LocalDateTime.of(2018, Month.APRIL, 15, 20, 30)) == 0);
        assertTrue(firstResult.getArrivalDate().compareTo(LocalDateTime.of(2018, Month.APRIL, 20, 20, 30)) == 0);
        assertEquals(firstResult.getDepartureAirportCode(), "LHR");
        assertEquals(firstResult.getDestinationAirportCode(), "AAA");
        assertEquals(firstResult.getSupplier(), ToughJetResponse.COMPANY_NAME);
        assertTrue(firstResult.getFare().compareTo(BigDecimal.valueOf(93.56)) == 0);

        assertEquals(secondResult.getAirline(), "airline1");
        assertTrue(secondResult.getDepartureDate().compareTo(LocalDateTime.of(2018, Month.APRIL, 15, 20, 30)) == 0);
        assertTrue(secondResult.getArrivalDate().compareTo(LocalDateTime.of(2018, Month.APRIL, 20, 20, 30)) == 0);
        assertEquals(secondResult.getDepartureAirportCode(), "LHR");
        assertEquals(secondResult.getDestinationAirportCode(), "AAA");
        assertTrue(secondResult.getFare().compareTo(BigDecimal.valueOf(100.00)) == 0);
    }
}
