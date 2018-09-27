package com.ub.contract.response;

import java.util.List;

public class EntityResponse<T> {

    private T data;
    private List<String> errors;

    public EntityResponse(T data){
        this.data = data;
    }

    public EntityResponse(List<String> errors){
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
