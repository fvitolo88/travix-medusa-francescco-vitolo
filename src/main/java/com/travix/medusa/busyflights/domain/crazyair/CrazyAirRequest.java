package com.travix.medusa.busyflights.domain.crazyair;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.travix.medusa.busyflights.deserializer.IsoLocalDateTimeDeserializer;
import com.travix.medusa.busyflights.serializer.IsoLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Wither
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrazyAirRequest {

    private String origin;
    private String destination;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime departureDate;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime returnDate;
    private int passengerCount;

    @Override
    public String toString() {
        return "CrazyAirRequest{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", passengerCount=" + passengerCount +
                '}';
    }
}
