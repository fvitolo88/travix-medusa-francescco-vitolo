package com.travix.medusa.busyflights.domain.crazyair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.travix.medusa.busyflights.deserializer.IsoLocalDateTimeDeserializer;
import com.travix.medusa.busyflights.domain.AbstractSearchFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.serializer.IsoLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Wither
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrazyAirResponse extends AbstractSearchFlightsResponse{

    public static final String COMPANY_NAME = "Crazy Air";

    private String airline;
    private double price;
    private String cabinclass;
    private String departureAirportCode;
    private String destinationAirportCode;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime departureDate;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime arrivalDate;

    @Override
    public BusyFlightsResponse buildResponse() {
        return new BusyFlightsResponse()
                .withAirline(airline)
                .withArrivalDate(arrivalDate)
                .withDepartureAirportCode(departureAirportCode)
                .withDestinationAirportCode(destinationAirportCode)
                .withDepartureDate(departureDate)
                .withFare(BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_CEILING))
                .withSupplier(COMPANY_NAME);
    }

    @Override
    public String toString() {
        return "CrazyAirResponse{" +
                "airline='" + airline + '\'' +
                ", price=" + price +
                ", cabinclass='" + cabinclass + '\'' +
                ", departureAirportCode='" + departureAirportCode + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
