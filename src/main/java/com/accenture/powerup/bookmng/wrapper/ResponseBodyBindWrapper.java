package com.accenture.powerup.bookmng.wrapper;

import java.util.ArrayList;
import java.util.List;

public class ResponseBodyBindWrapper {

    private String status;
    private Object result;
    private List<FieldErrorEntity> errors = new ArrayList<>();

    public ResponseBodyBindWrapper(String status) {
        this.status = status;
    }

    public ResponseBodyBindWrapper(String status, Object result) {
        this.status = status;
        this.result = result;
    }

    public ResponseBodyBindWrapper(String status, Object result, List<FieldErrorEntity> errors) {
      this.status = status;
      this.result = result;
      this.errors = errors;
  }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<FieldErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorEntity> errors) {
        this.errors = errors;
    }
}
