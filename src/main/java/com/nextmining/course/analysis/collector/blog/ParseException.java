package com.nextmining.course.analysis.collector.blog;

public class ParseException extends Exception {

    private static final long serialVersionUID = 0;

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable t) {
        super();
        initCause(t);
    }

    public ParseException(String message, Throwable t) {
        super(message);
        initCause(t);
    }
}
