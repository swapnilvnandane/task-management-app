package com.app.task.exception;

import java.io.Serial;

/**
 * Exception when task execution error.
 *
 * */
public class TaskExecutionException extends RuntimeException {

    /** Serial version **/
    @Serial
    private static final long serialVersionUID = -5019049871899374861L;

    /**
     * Constructor.
     *
     * @param message Exception message.
     *
     * */
    public TaskExecutionException(String message) {
        super(message);
    }
}
