package model;

import java.time.LocalDateTime;

public class DailyRepeatable implements Repeatable {

    @Override
    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusDays(1);
    }

    @Override
    public String getStringRepresentation() {
        return "Ежедневная";
    }
}
