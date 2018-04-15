package com.travix.medusa.busyflights.domain.toughjet;

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
public class ToughJetRequest {

    private String from;
    private String to;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime outboundDate;
    @JsonDeserialize(using = IsoLocalDateTimeDeserializer.class)
    @JsonSerialize(using = IsoLocalDateTimeSerializer.class)
    private LocalDateTime inboundDate;
    private int numberOfAdults;

    @Override
    public String toString() {
        return "ToughJetRequest{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", outboundDate=" + outboundDate +
                ", inboundDate=" + inboundDate +
                ", numberOfAdults=" + numberOfAdults +
                '}';
    }
}
