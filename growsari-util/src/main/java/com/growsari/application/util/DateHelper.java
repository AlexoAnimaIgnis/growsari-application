package com.growsari.application.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public final class DateHelper {
    static final int MAX_HOUR = 23;
    static final int MAX_SECOND = 59;
    static final int MAX_MINUTE = 59;
    static final int MAX_NANO = 999999999;

    private DateHelper() {
        //do nothing
    }

    public static LocalDateTime toStartOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atStartOfDay();
        } else {
            return null;
        }
    }

    public static LocalDateTime toEndOfDay(LocalDate localDate) {
        if (localDate != null) {
            return localDate.atTime(MAX_HOUR, MAX_MINUTE, MAX_SECOND, MAX_NANO);
        } else {
            return null;
        }
    }

    public static Integer calcMonthFrom(LocalDate localDateFrom, int year) {
        if (localDateFrom != null) {
            if (year > localDateFrom.getYear()) {
                return Month.JANUARY.getValue();
            } else if (year == localDateFrom.getYear()) {
                return localDateFrom.getMonthValue();
            } else {
                throw new IllegalStateException("Wrong year selection");
            }
        } else {
            return null;
        }
    }

    public static Integer calcMonthTo(LocalDate localDateTo, int year) {
        if (localDateTo != null) {
            if (localDateTo.getYear() > year) {
                return Month.DECEMBER.getValue();
            } else if (localDateTo.getYear() == year) {
                return localDateTo.getMonthValue();
            } else {
                throw new IllegalStateException("Wrong year selection");
            }
        } else {
            return null;
        }
    }

    public static Integer calcDayFrom(LocalDate localDateFrom, int month, int year) {
        if (localDateFrom != null) {
            if (year > localDateFrom.getYear() || month > localDateFrom.getMonthValue()) {
                return 1;
            } else if (year < localDateFrom.getYear() || month < localDateFrom.getMonthValue()) {
                throw new IllegalStateException("Wrong year or month selection");
            } else {
                return localDateFrom.getDayOfMonth();
            }
        } else {
            return null;
        }
    }

    public static Integer calcDayTo(LocalDate localDateTo, int month, int year) {
        if (localDateTo != null) {
            if (localDateTo.getYear() > year || localDateTo.getMonthValue() > month) {
                return YearMonth.of(year, month).lengthOfMonth();
            } else if (year > localDateTo.getYear() || month > localDateTo.getMonthValue()) {
                throw new IllegalStateException("Wrong year or month selection");
            } else {
                return localDateTo.getDayOfMonth();
            }
        } else {
            return null;
        }
    }

    public static Date toDate(String value, String format) {
        if (value == null) {
            return null;
        } else {
            DateTimeFormatter dft = DateTimeFormatter.ofPattern(format);
            LocalDate localDate = LocalDate.parse(value, dft);

            return Date.valueOf(localDate);
        }
    }

    public static String toString(Date sqlDate, String format) {
        if (sqlDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(sqlDate);
        } else {
            return null;
        }
    }

    public static LocalDateTime toLocalDateTime(String text, DateTimeFormatter formatter) {
        return text != null ? LocalDateTime.parse(text, formatter) : null;
    }
}