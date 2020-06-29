package ru.ruslan.exception;

public class VerificationTokenExpiredException extends RuntimeException {
    public VerificationTokenExpiredException() {
        super("The code has been resent");
    }

}