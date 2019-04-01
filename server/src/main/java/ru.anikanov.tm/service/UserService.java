package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.UserMapper;
import ru.anikanov.tm.utils.PasswordHashUtil;

import java.util.List;
import java.util.Objects;

public class UserService implements IUserService {
    @NotNull
    private UserMapper userRepository;

    public UserService(@NotNull final UserMapper userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User persist(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                        @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return null;
        if (userRepository.findOne(login) == null) {
            if ((firstName == null) || firstName.isEmpty()) return null;
            if ((lastName == null) || lastName.isEmpty()) return null;
            if ((email == null) || email.isEmpty()) return null;
            if ((password == null) || password.isEmpty()) return null;
            if (role == null) return null;
            User user = new User(login, firstName, lastName, email, password, role);
            userRepository.persist(user);
            return user;
        }
        return null;
    }

    public void merge(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                      @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return;
        if ((firstName == null) || firstName.isEmpty()) return;
        if ((lastName == null) || lastName.isEmpty()) return;
        if ((email == null) || email.isEmpty()) return;
        if ((password == null) || password.isEmpty()) return;
        if (role == null) return;
        userRepository.merge(new User(login, firstName, lastName, email, password, role));
    }

    public boolean logIn(@Nullable final String login, @Nullable final String password) {
        if ((login == null) || login.isEmpty()) return false;
        if ((password == null) || password.isEmpty()) return false;
        @Nullable final User user = findOne(login, login);
        if (user == null) return false;
        return Objects.equals(user.getHashPassword(), PasswordHashUtil.md5(password));

    }

    public void updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne) {
        if ((login == null) || login.isEmpty()) return;
        if ((oldOne == null) || oldOne.isEmpty()) return;
        if ((newOne == null) || newOne.isEmpty()) return;
        userRepository.updatePassword(login, oldOne, newOne);
    }

    @Nullable
    public User findOne(@Nullable final String login, @NotNull final String userId) {
        if ((login == null) || login.isEmpty()) return null;
        return userRepository.findOne(login);
    }

    @Nullable
    public List<User> findAll(@NotNull final String userId) {
        return userRepository.findAll();
    }

    public void remove(@Nullable final String login, @NotNull final String userId) {
        if ((login == null) || login.isEmpty()) return;
        userRepository.remove(login);
    }

    public void removeAll(@NotNull final String userId) {
        userRepository.removeAll();
    }

    public boolean checkadmin(@NotNull final Session session) {
        if (session.getUserId() == null) return false;
        return Objects.requireNonNull(Objects.requireNonNull(findOne(session.getUserId(), session.getUserId())).getRole()).equals(Role.ADMIN);
    }

    public User findByName(@NotNull final String name) {
        return userRepository.findByName(name);
    }

    public User getCurrentUser(@NotNull final Session session) {
        return userRepository.findOne(Objects.requireNonNull(session.getUserId()));
    }
}
