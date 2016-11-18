package com.epam.ilya.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class contains main field needs to all model entities
 *
 * @author Ilya_Bondarenko
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ" , sequenceName = "NEWS_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "SEQ",strategy = GenerationType.SEQUENCE)
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
