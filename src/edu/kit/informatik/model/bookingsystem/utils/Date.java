package edu.kit.informatik.model.bookingsystem.utils;

public record Date(int date) {

    public int getDate() {
        return date;
    }

    public boolean dateIsGreaterOrEqual(Date date) {
        return this.date >= date.getDate();
    }
}
