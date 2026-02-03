package bame.domain.repository;

import java.util.Optional;

import bame.domain.model.User;

public interface UserRepository {
	void save(User user);

    Optional<User> findById(String userId);
}
