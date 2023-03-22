package nl.cim.training.springboot.utilities;

import java.time.LocalDate;
import java.time.Period;

public class DateTimeUtils {
    public static final int getYearsDifference(LocalDate start, LocalDate end) {
        return Period.between(start, end).getYears();
    }
}
