package com.app.task.exception;

import java.io.Serial;

/**
 * Exception if task not found.
 *
 * */
public class TaskNotFound extends Exception {

    /** Serial version. **/
    @Serial
    private static final long serialVersionUID = -3779298831881607576L;

    /**
     * Constructor.
     *
     * @param message Exception message.
     *
     * */
    public TaskNotFound(String message) {
        super(message);
    }
}
