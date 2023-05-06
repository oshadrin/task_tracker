package model;

import java.time.LocalDateTime;

public class WeeklyRepeatable implements Repeatable {

    @Override
    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return currentDateTime.plusWeeks(1);
    }

    @Override
    public String getStringRepresentation() {
        return "Еженедельная";
    }
}
