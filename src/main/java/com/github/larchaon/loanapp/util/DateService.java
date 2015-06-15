package com.github.larchaon.loanapp.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateService {

    public Date now() {
        return new Date();
    }

    public Calendar nowCalendar() {
        return Calendar.getInstance();
    }
}
