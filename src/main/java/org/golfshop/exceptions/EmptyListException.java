package org.golfshop.exceptions;

import jakarta.ws.rs.WebApplicationException;

public class EmptyListException extends WebApplicationException {
    public EmptyListException() {
        super();
    }
    public EmptyListException(String message) {
        super("EmptyListException Error: " + message);
    }
}