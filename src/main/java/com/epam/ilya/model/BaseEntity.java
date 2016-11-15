package com.epam.ilya.model;

import java.io.Serializable;

/**
 * Class contains main field needs to all model entities
 *
 * @author Ilya_Bondarenko
 */

public class BaseEntity implements Serializable{

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
