package com.travix.medusa.busyflights.serializer;

import java.time.format.DateTimeFormatter;

public class IsoLocalDateTimeSerializer extends LocalDateTimeSerializer {

    @Override
    protected DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }
}