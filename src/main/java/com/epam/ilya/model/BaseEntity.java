package com.epam.ilya.model;

public class BaseEntity {
    public static final int ACTIVE = 1;
    public static final int DELETED = 0;

    private int id;
    private int status = ACTIVE;

    public BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
