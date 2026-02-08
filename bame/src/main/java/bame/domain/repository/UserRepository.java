package bame.domain.repository;

import java.util.Optional;

import bame.domain.model.User;

public interface UserRepository {

	/**
	 * Save an user.
	 * @param user the user to be saved.
	 * */
	void save(User user);

	/**
	 * Find user by id.
	 * @param userId id user.
	 * @return User if exists, else null.
	 * */
    Optional<User> findById(String userId);
}
