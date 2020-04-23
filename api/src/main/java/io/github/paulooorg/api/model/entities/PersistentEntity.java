package io.github.paulooorg.api.model.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentEntity implements BaseEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
