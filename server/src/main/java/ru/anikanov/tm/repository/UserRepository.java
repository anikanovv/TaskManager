package ru.anikanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.anikanov.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User save(@NotNull final User user);

    void delete(@NotNull final User user);

    Optional<User> findById(@NotNull final String id);

    List<User> findAll();

    void deleteAll();

    User findByName(@NotNull final String login);

    User findByNameAndHashPassword(@NotNull final String login, @NotNull final String password);
}
