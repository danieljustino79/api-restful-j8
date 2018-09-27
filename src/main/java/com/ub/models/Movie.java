package com.ub.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class Movie {

    @Id
    private int id;

    @NotEmpty(message = "Field name is required.")
    @Length(min = 1, max = 80, message = "Field name size must be between {min} and {max}.")
    private String name;

    @NotNull(message = "Field date is required.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Calendar date;

    public Movie() { }

    public Movie(int id, String name, Calendar date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
