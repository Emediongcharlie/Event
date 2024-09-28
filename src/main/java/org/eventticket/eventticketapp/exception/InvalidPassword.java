package org.eventticket.eventticketapp.exception;

public class InvalidPassword extends RuntimeException {
    public InvalidPassword(String message) {
        super("InvalidPassword");
    }

    }

