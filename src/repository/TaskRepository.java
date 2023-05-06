package repository;

import model.Repeatable;
import model.Task;
import model.TaskType;
import util.GeneratorUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {
    private final Map<Long, Task> tasks = new HashMap<>();

    public long add(String title,
                    String description,
                    TaskType type,
                    LocalDateTime dateTime,
                    Repeatable repeat) {
        Long taskId = GeneratorUtil.generateLong();
        Task task = new Task(taskId, title, description, type, dateTime, repeat);
        tasks.put(taskId, task);
        System.out.println("Задача добавлена. ID задачи: " + taskId);
        return taskId;
    }
    public void removeById(Long id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            System.out.println("Задача с ID " + id + " удалена.");
        } else {
            System.out.println("Задача с ID " + id + " не найдена.");
        }
    }
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }
}
