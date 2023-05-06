package model;

public enum TaskType {
    PERSONAL("Личная"),
    WORK("Рабочая");

    private final String value;

    TaskType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
