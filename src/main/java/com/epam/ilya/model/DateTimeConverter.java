package com.epam.ilya.model;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;

@Converter
public class DateTimeConverter implements AttributeConverter<DateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(DateTime dateTime) {
        return new Date(dateTime.getMillis());
    }

    @Override
    public DateTime convertToEntityAttribute(Date dbData) {
        return new DateTime(dbData);
    }
}
