package com.example.ride.util;

public class LimitExhaustedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final int limit;

    public LimitExhaustedException(String message, int limit) {
        super(String.format("%s. Limit: %d", message, limit));
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}