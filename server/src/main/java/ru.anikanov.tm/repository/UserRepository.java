package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository implements IUserRepository {
    @Getter
    private Map<String, User> userMap = new LinkedHashMap<>();

    @NotNull
    public User persist(@NotNull final User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    public User merge(@NotNull final User u) {
        @NotNull final User user = userMap.get(u.getName());
        user.setHashPassword(u.getHashPassword());
        user.setRole(u.getRole());
        return user;
    }

    public boolean logIn(@NotNull final String login, @NotNull final String password) {
        @Nullable final User user = findByName(login);
        if (user == null) return false;
        return user.getHashPassword().equals(password);
    }

    public boolean logOut() {
        return true;
    }

    public boolean updatePassword(@NotNull final String login, @NotNull final String oldOne, @NotNull final String newOne) {
        @Nullable User user = userMap.get(login);
        if (user == null) return false;
        if (user.getHashPassword().equals(oldOne)) {
            user.setHashPassword(newOne);
            return true;
        } else return false;

    }

    @Nullable
    public User findOne(@NotNull final String login) {
        return userMap.get(login);
    }

    @NotNull
    public List<User> findAll() {
        @Nullable List<User> users = new ArrayList<>();
        userMap.forEach((k, v) -> users.add(v));
        return users;
    }


    public void remove(@NotNull final String login) {
        userMap.remove(login);
    }

    public void removeAll() {
        userMap.clear();
    }

    public User findByName(@NotNull final String name) {
        User newUser = null;
        for (User user : findAll()) {
            if (Objects.requireNonNull(user.getName()).equals(name)) {
                newUser = user;
                break;
            }
        }
        return newUser;
    }

}