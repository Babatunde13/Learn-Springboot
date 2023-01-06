package com.babatunde.learningspring.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    public Date createDateFromString(String dateString) {
        DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch(ParseException e) {
            date = new Date();
        }

        return date;
    }
}
