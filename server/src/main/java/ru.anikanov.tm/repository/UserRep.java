package ru.anikanov.tm.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.User;

import java.util.List;

@Repository
public interface UserRep extends EntityRepository<User, String> {
    void persist(@NotNull final User user);

    void merge(@NotNull final User u);

    User findOne(@NotNull final String id);

    List<User> findAll();

    void remove(@NotNull final User user);

    void removeAll();

    @Query("SELECT u FROM User u WHERE u.name = :login")
    User findByName(@QueryParam("login") @NotNull final String login);

    @Query("SELECT u FROM User u WHERE u.name = :login AND u.hashPassword = :password")
    User logIn(@QueryParam("login") @NotNull final String login, @QueryParam("password") @NotNull final String password);
}
