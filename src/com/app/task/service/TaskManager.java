package com.app.task.service;

import com.app.task.exception.TaskExecutionException;
import com.app.task.exception.TaskNotFound;
import com.app.task.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Task manager functionality.
 */
public class TaskManager {

    /** List to store all task data. **/
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Find task by task id.
     *
     * @param taskId Task id.
     * @return {@link Task} data matches to given task id.
     * @throws {@link TaskNotFound} if task not present for given task id.
     *
     * */
    private Task findTaskById(int taskId) throws TaskNotFound {
        return tasks.stream().filter(t -> t.getTaskId() == taskId).findFirst()
                .orElseThrow(() -> new TaskNotFound("Task with id " + taskId + " not found"));
    }

    /**
     * Create task.
     *
     * @param description Task description.
     * @param priority Task priority.
     * @param dueDate Task due date.
     * */
    public void createTask(String description, int priority, LocalDate dueDate) {
        tasks.add(new Task(description, priority, dueDate));
    }

    /**
     * Update task for given task id.
     *
     * @param taskId Task id.
     * @param description Task description.
     * @param priority Task priority.
     * @param dueDate Task due date.
     * @param status Task status {@link Task.TaskStatus}.
     * @throws {@link TaskNotFound} if task not found for given task id.
     * */
    public void updateTask(int taskId, String description, int priority, LocalDate dueDate,
                           Task.TaskStatus status) throws TaskNotFound {
        Task task = findTaskById(taskId);
        task.setTaskDescription(description);
        task.setTaskPriority(priority);
        task.setTaskDueDate(dueDate);
        task.setTaskStatus(status);
    }

    /**
     * Delete task for given task id.
     *
     * @param taskId Task id.
     * @throws {@link TaskNotFound} if task not found for given task id.
     * */
    public void deleteTask(int taskId) throws TaskNotFound {
        Task task = findTaskById(taskId);
        tasks.remove(task);
    }

    /**
     * Filter task by given priority.
     *
     * @param priority Task priority.
     * */
    public List<Task> filterTasksByPriority(int priority) {
        return tasks.stream().filter(f -> f.getTaskPriority() == priority).toList();
    }

    /**
     * Sort tasks by due date and priority.
     *
     * @return {@link List<Task>} with sorted data.
     * */
    public List<Task> sortTasksByDueDateAndPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getTaskDueDate).thenComparing(Task::getTaskPriority)).toList();
    }

    /**
     * Execute concurrent tasks.
     *
     * @throws {@link TaskExecutionException} if any task already in progress.
     * */
    public void executeConcurrentTasks() {
        tasks.parallelStream().forEach(task -> {
           synchronized (task) {
               if (task.getTaskStatus() == Task.TaskStatus.IN_PROGRESS) {
                   throw new TaskExecutionException("Task with ID " + task.getTaskId() + " is already being executed.");
               }
               task.setTaskStatus(Task.TaskStatus.IN_PROGRESS);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
               task.setTaskStatus(Task.TaskStatus.COMPLETED);
           }
        });
    }
}
