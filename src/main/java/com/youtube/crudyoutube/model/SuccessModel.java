package com.youtube.crudyoutube.model;

public class SuccessModel {
    private Boolean success;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FailureModel{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
