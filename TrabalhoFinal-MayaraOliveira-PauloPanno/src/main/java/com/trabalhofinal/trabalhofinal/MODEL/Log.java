package com.trabalhofinal.trabalhofinal.MODEL;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.time.LocalTime;

@XStreamAlias("log")
public class Log {

    private String operation;
    private String name;
    private String username;
    private String exception;
    private LocalDate date;
    private LocalTime time;

    public Log(String operation, String name, String username, String exception) {
        this.operation = operation;
        this.name = name;
        this.username = username;
        this.exception = exception;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
