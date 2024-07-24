package de.earzt.backend.core.exceptions.types;

public class HaveActiveRecordException extends RuntimeException{
    public HaveActiveRecordException(String message) {
        super(message);
    }
}
