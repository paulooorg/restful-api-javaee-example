package io.github.paulooorg.api.model.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentEntity implements BaseEntity<Long> {

}
