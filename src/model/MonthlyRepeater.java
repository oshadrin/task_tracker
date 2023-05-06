package model;


import java.time.LocalDateTime;

public class MonthlyRepeater implements Repeatable {

    @Override
    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusMonths(1);
    }

    @Override
    public String getStringRepresentation() {
        return "Ежемесячная";
    }
}
