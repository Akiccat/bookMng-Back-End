package com.accenture.powerup.bookmng.exception;

import org.springframework.validation.Errors;

public class BusinessFailureException extends RuntimeException {

    private final transient Errors errors;
    private final transient boolean isFormInvalid;

    public BusinessFailureException() {
        super();
        this.errors = null;
        this.isFormInvalid = true;
    }

    public BusinessFailureException(Throwable cause) {
        super(cause);
        this.errors = null;
        this.isFormInvalid = true;
    }

    public BusinessFailureException(Errors errors) {
        super();
        this.errors = errors;
        this.isFormInvalid = true;
    }

    public BusinessFailureException(Errors errors, Throwable cause) {
        super(cause);
        this.errors = errors;
        this.isFormInvalid = true;
    }

    public BusinessFailureException(boolean isFormInvalid) {
        super();
        this.errors = null;
        this.isFormInvalid = isFormInvalid;
    }

    public Errors getErrors() {
        return errors;
    }

    public boolean getIsFormInvalid() {
        return isFormInvalid;
    }
}
