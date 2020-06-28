package io.github.paulooorg.api.model.entities;

import java.io.Serializable;

public interface BaseEntity<PK extends Serializable> {
    PK getId();
    
    void setId(PK id);
}
