package org.launch_code.fetch_a_friend.exception;

public class FileNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileNotSupportedException(String message) {
        super(message);
    }
}
