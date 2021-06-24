package org.mentor.exceptions;

public class TriedToPurchaseMoreThanStoredException extends Exception{

    public TriedToPurchaseMoreThanStoredException(String message) {
        super(message);
    }
}
