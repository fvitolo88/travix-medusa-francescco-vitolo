package com.travix.medusa.busyflights.domain.toughjet;

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
public class ToughJetResponse extends AbstractSearchFlightsResponse{

    public static final String COMPANY_NAME = "Tough Jet";

    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime outboundDateTime;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime inboundDateTime;

    @Override
    public BusyFlightsResponse buildResponse() {
        BigDecimal basePrice = BigDecimal.valueOf(getBasePrice());
        BigDecimal tax = BigDecimal.valueOf(getTax());
        BigDecimal discount = BigDecimal.valueOf(getDiscount());
        BigDecimal totalPrice = basePrice.add(tax).subtract(discount).setScale(2,BigDecimal.ROUND_CEILING);
        return new BusyFlightsResponse()
                .withAirline(carrier)
                .withFare(totalPrice)
                .withDepartureAirportCode(departureAirportName)
                .withDestinationAirportCode(arrivalAirportName)
                .withDepartureDate(outboundDateTime)
                .withArrivalDate(inboundDateTime)
                .withSupplier(COMPANY_NAME);
    }

    @Override
    public String toString() {
        return "ToughJetResponse{" +
                "carrier='" + carrier + '\'' +
                ", basePrice=" + basePrice +
                ", tax=" + tax +
                ", discount=" + discount +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", outboundDateTime=" + outboundDateTime +
                ", inboundDateTime=" + inboundDateTime +
                '}';
    }
}
