package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.User;

public class UserRepository extends AbstractEntityRepository<User, Long> {
    public UserRepository() {
        super(User.class);
    }
}
