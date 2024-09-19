package com.example.reminder;

public class Reminder {
    private String time;
    private String message;

    public Reminder(String time, String message) {
        this.time = time;
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}
