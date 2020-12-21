package com.tasktracker.application.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Formatter {

    public static Date toDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
