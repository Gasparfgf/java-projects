package bame.infrastructure.persistence.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bame.domain.model.User;
import bame.domain.repository.UserRepository;

public final class InMemoryUserRepository implements UserRepository {
	/** Account storage. */
    private final Map<String, User> storage = new HashMap<>();

    @Override
    public void save(final User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(final String userId) {
        return Optional.ofNullable(storage.get(userId));
    }

}
