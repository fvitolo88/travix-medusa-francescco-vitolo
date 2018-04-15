package com.travix.medusa.busyflights.deserializer;

import java.time.format.DateTimeFormatter;

public class IsoLocalDateTimeDeserializer extends LocalDateTimeDeserializer {

    @Override
    protected DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }
}