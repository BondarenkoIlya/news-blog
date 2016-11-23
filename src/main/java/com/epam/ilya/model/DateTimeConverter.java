package com.epam.ilya.model;

import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;

/**
 * Class converts dates in understandable for persistence format and back
 *
 * @author Ilya_Bondarenko
 */
@Converter
public class DateTimeConverter implements AttributeConverter<DateTime, Date> {

    /**
     * Method converts DateTime to sql Date
     *
     * @param dateTime to convert
     * @return converted date
     */
    @Override
    public Date convertToDatabaseColumn(DateTime dateTime) {
        return new Date(dateTime.getMillis());
    }

    /**
     * Method converts sql Date to DateTime
     *
     * @param dbData to convert
     * @return converted dateTime
     */
    @Override
    public DateTime convertToEntityAttribute(Date dbData) {
        return new DateTime(dbData);
    }
}
