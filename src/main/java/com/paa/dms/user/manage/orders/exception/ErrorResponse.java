package com.paa.dms.user.manage.orders.exception;

import lombok.Data;

import java.util.Date;
@Data
/**
 * Represents a standardized error response structure for API error messages.
 * This class encapsulates the details of an error that occurs during API
 * requests, including the HTTP status code, error message, and request details.
 */
public class ErrorResponse {
    private Date timestamp;     // The timestamp when the error occurred
    private int statusCode;     // HTTP status code representing the error type
    private String message;      // A brief message describing the error
    private String details;      // Additional details about the error (e.g., request context)

    /**
     * Constructs an ErrorResponse instance with the provided status code,
     * message, and details. The timestamp is automatically set to the
     * current date and time.
     *
     * @param statusCode the HTTP status code representing the error type
     * @param message a brief message describing the error
     * @param details additional details about the error (e.g., request context)
     */
    public ErrorResponse(int statusCode, String message, String details) {
        this.timestamp = new Date();
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    // Getters for the error response fields
    public Date getTimestamp() {
        return timestamp;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
}
