package com.app.task.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task Model
 *
 * */
public class Task {

    /** counter for auto-increment **/
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    /** Task id **/
    private int taskId;

    /** Task description  **/
    private String taskDescription;

    /** Task priority **/
    private int taskPriority;

    /** Task status **/
    private TaskStatus taskStatus;

    /** Task due date **/
    private LocalDate taskDueDate;

    /** Task status enum **/
    public enum TaskStatus {
        IN_PROGRESS, PENDING, COMPLETED, FAILED;
    }

    /**
     * Parameterised constructor
     *
     * @param taskDescription Task description
     * @param taskPriority Task priority
     * @param taskDueDate Task due date
     * **/
    public Task(String taskDescription, int taskPriority, LocalDate taskDueDate) {
        this.taskDescription = taskDescription;
        this.taskPriority = taskPriority;
        this.taskDueDate = taskDueDate;
        this.taskId = COUNTER.incrementAndGet();
        this.taskStatus = TaskStatus.PENDING;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskPriority=" + taskPriority +
                ", taskStatus=" + taskStatus +
                ", taskDueDate=" + taskDueDate +
                '}';
    }
}
