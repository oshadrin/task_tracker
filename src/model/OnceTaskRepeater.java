package model;

import java.time.LocalDateTime;

public class OnceTaskRepeater implements Repeatable {
    @Override
    public LocalDateTime getNextDateTime(LocalDateTime currentDateTime) {
        return null;
    }

    @Override
    public String getStringRepresentation() {
        return "Однократная";
    }
}
