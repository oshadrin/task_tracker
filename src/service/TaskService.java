package service;

import model.OnceTaskRepeater;
import model.Repeatable;
import model.Task;
import model.TaskType;
import repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForDate(LocalDate checkDate) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            LocalDateTime nextTaskDateTime = task.getDateTime();
            if (task.getTaskRepeater() instanceof OnceTaskRepeater
                    && !nextTaskDateTime.toLocalDate().equals(checkDate)) {
                continue;
            }
            while (nextTaskDateTime.toLocalDate().isBefore(checkDate) || nextTaskDateTime.toLocalDate().equals(checkDate)) {
                if (nextTaskDateTime.toLocalDate().equals(checkDate)) {
                    tasksForDate.add(task);
                    break;
                }
                nextTaskDateTime = task.getTaskRepeater().getNextDateTime(nextTaskDateTime);
            }
        }
        return tasksForDate;
    }

    public void removeTask(long id) {
        taskRepository.removeById(id);
    }

    public long addTask(String title,
                        String description,
                        TaskType taskType,
                        LocalDateTime localDateTime,
                        Repeatable repeatable) {
        return taskRepository.add(title, description, taskType, localDateTime, repeatable);
    }
}
