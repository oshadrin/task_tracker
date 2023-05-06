package model;

import java.time.LocalDateTime;

public class YearlyRepeater implements Repeatable {

    @Override
    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusYears(1);
    }

    @Override
    public String getStringRepresentation() {
        return "Ежегодная";
    }
}
