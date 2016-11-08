package com.epam.ilya.model;

/**
 * Class contains main field needs to all model entities
 *
 * @author Ilya_Bondarenko
 */

public class BaseEntity {

    private int id;

    public BaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
