package org.golfshop.exceptions;

import jakarta.ws.rs.WebApplicationException;

public class InvalidIdException extends WebApplicationException {
    public InvalidIdException() {
        super();
    }
    public InvalidIdException(String message) {
        super("InvalidIdException Error: " +message);
    }
}
