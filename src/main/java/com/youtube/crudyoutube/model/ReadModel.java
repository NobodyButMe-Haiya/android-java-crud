package com.youtube.crudyoutube.model;

import java.util.List;

public class ReadModel {
    private Boolean success;
    private String message;
    // but here we want to retrieve data json array
    private List<DataModel> data;
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

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReadModel{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
