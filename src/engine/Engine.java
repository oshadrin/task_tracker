package engine;

import model.*;
import service.TaskService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Engine {
    private final TaskService taskService;
    public Engine(TaskService taskService) {
        this.taskService = taskService;
    }
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    scanner.nextLine();
                    switch (menu) {
                        case 1 -> inputTask(scanner);
                        case 2 -> removeTask(scanner);
                        case 3 -> getTasksForDate(scanner);
                        case 0 -> {
                            return;
                        }
                        default -> System.out.println("Выберите пункт меню из списка!");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }
    private void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачи на указанную дату");
        System.out.println("0. Выход");
    }
    private void getTasksForDate(Scanner scanner) {
        List<Task> tasks = taskService.getTasksForDate(getLocalDate(scanner));
        if (tasks.isEmpty()) {
            System.out.println("На указанную дату нет задач.");
        } else {
            System.out.println("Задачи на указанную дату:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
    private LocalDateTime getLocalDateTime(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите дату выполнения задачи (в формате dd.MM.yyyy HH:mm): ");
                String dateTimeStr = scanner.nextLine().trim();
                return LocalDateTime.parse(dateTimeStr,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты! Попробуйте снова.");
            }
        }
    }
    private LocalDate getLocalDate(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите дату выполнения задачи (в формате dd.MM.yyyy): ");
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Некорректный формат даты! Попробуйте снова.");
            }
        }
    }
    private void removeTask(Scanner scanner) {
        System.out.print("Введите ID задачи для удаления: ");
        if (scanner.hasNextLong()) {
            long taskId = scanner.nextLong();
            taskService.removeTask(taskId);
        }
    }
    private void inputTask(Scanner scanner) {
        String title = null;
        while (title == null || title.trim().isEmpty()) {
            System.out.print("Введите название задачи: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Название задачи не может быть пустым");
            }
        }

        String description = null;
        while (description == null || description.trim().isEmpty()) {
            System.out.print("Введите описание задачи: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Описание задачи не может быть пустым");
            }
        }

        TaskType type = getTaskType(scanner);
        LocalDateTime localDateTime = getLocalDateTime(scanner);
        Repeatable repeatable = getTaskRepeatable(scanner);
        taskService.addTask(title, description, type, localDateTime, repeatable);
    }
    private TaskType getTaskType(Scanner scanner) {
        TaskType type = null;
        while (type == null) {
            System.out.println("Выберите тип задачи: ");
            System.out.println("1. Личная");
            System.out.println("2. Рабочая");
            System.out.print("Ваш выбор: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> type = TaskType.PERSONAL;
                    case 2 -> type = TaskType.WORK;
                    default -> System.out.println("Выберите тип задачи из списка!");
                }
            } else {
                scanner.next();
                System.out.println("Выберите тип задачи из списка!");
            }
        }
        return type;
    }
    private Repeatable getTaskRepeatable(Scanner scanner) {
        Repeatable repeatable = null;
        while (repeatable == null) {
            System.out.println("Выберите повторяемость задачи: ");
            System.out.println("1. Однократная");
            System.out.println("2. Ежедневная");
            System.out.println("3. Еженедельная");
            System.out.println("4. Ежемесячная");
            System.out.println("5. Ежегодная");
            System.out.print("Ваш выбор: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> repeatable = new OnceTaskRepeater();
                    case 2 -> repeatable = new DailyRepeatable();
                    case 3 -> repeatable = new WeeklyRepeatable();
                    case 4 -> repeatable = new MonthlyRepeater();
                    case 5 -> repeatable = new YearlyRepeater();
                    default -> System.out.println("Выберите повторяемость задачи из списка!");
                }
            } else {
                scanner.next();
                System.out.println("Выберите повторяемость задачи из списка!");
            }
        }
        return repeatable;
    }
}
