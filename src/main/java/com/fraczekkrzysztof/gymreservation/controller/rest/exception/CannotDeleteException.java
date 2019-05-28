package com.fraczekkrzysztof.gymreservation.controller.rest.exception;

public class CannotDeleteException extends RuntimeException {
    public CannotDeleteException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CannotDeleteException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }

    public CannotDeleteException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        // TODO Auto-generated constructor stub
    }

    public CannotDeleteException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public CannotDeleteException(Throwable arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
}
