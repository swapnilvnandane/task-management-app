import com.app.task.exception.TaskExecutionException;
import com.app.task.exception.TaskNotFound;
import com.app.task.model.Task;
import com.app.task.service.TaskManager;

import java.time.LocalDate;

/**
 * Main method
 *
 * */
public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Create Tasks.
        manager.createTask("Task 1", 1, LocalDate.of(2024, 7, 13));
        manager.createTask("Task 2", 2, LocalDate.of(2024, 7, 14));
        manager.createTask("Task 3", 3, LocalDate.of(2024, 7, 15));

        // Print all tasks before all operation.
        System.out.println("\n\nAll tasks before all operations: ");
        manager.sortTasksByDueDateAndPriority().forEach(System.out::println);

        // Update a task.
        try {
            manager.updateTask(1, "Update Task 1", 1,
                    LocalDate.of(2024, 7, 12), Task.TaskStatus.PENDING);
        } catch (TaskNotFound e) {
            System.out.println(e.getMessage());
        }

        // Delete Task.
        try {
            manager.deleteTask(2);
        } catch (TaskNotFound e) {
            System.out.println(e.getMessage());
        }

        // Filter task by priority.
        System.out.println("\n\nTasks with priority 1: ");
        manager.filterTasksByPriority(1).forEach(System.out::println);

        // Sort tasks by due date and priority
        System.out.println("\n\nTasks with sorted data: ");
        manager.sortTasksByDueDateAndPriority().forEach(System.out::println);

        // Execute concurrent tasks.
        try {
            manager.executeConcurrentTasks();
        } catch (TaskExecutionException e) {
            System.out.println(e.getMessage());
        }

        // Print all tasks after all operation.
        System.out.println("\n\nAll tasks after all operations: ");
        manager.sortTasksByDueDateAndPriority().forEach(System.out::println);
    }
}