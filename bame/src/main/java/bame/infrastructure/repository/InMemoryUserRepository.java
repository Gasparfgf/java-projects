package bame.infrastructure.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bame.domain.model.User;
import bame.domain.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> storage = new HashMap<>();

    @Override
    public void save(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(storage.get(userId));
    }

}
