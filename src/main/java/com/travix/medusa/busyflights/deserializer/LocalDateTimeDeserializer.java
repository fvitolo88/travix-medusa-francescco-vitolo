package com.travix.medusa.busyflights.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
public abstract class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    protected abstract DateTimeFormatter getDateFormatter();

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
        String date = null;
        try {
            date = jsonParser.getText();
        } catch (Exception e) {
            log.warn("Error parsing date in IsoDateTimeDeserializer",e);
        }
        if(Optional.ofNullable(date).isPresent()) {
            try {
                return LocalDateTime.parse(date,getDateFormatter());
            } catch (Exception e) {
                log.warn(String.format("Error deserializing date : %s", date), e);
            }
        }
        return null;
    }
}
