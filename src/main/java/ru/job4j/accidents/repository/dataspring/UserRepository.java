package ru.job4j.accidents.repository.dataspring;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
