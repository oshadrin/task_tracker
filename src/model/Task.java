package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    private final Long id;
    private String title;
    private String description;
    private TaskType type;
    private LocalDateTime dateTime;
    private Repeatable repeatable;

    public Task(Long id,
                String title,
                String description,
                TaskType type,
                LocalDateTime dateTime,
                Repeatable repeatable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
        this.repeatable = repeatable;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public Repeatable getTaskRepeater() {
        return repeatable;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type.getValue() +
                ", dateTime=" + dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", taskRepeater=" + repeatable.getStringRepresentation() +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
