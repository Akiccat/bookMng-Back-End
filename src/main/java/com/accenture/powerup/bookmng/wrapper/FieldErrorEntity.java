package com.accenture.powerup.bookmng.wrapper;

public class FieldErrorEntity {

    private String message;
    private String field;

    public FieldErrorEntity() {
    }

    public FieldErrorEntity(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
