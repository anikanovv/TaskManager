package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;
import java.util.Objects;

public class UserService implements IUserService {
    @NotNull
    private IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
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
            return userRepository.persist(new User(login, firstName, lastName, email, password, role));
        }
        return null;
    }

    public User merge(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                      @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return null;
        if ((firstName == null) || firstName.isEmpty()) return null;
        if ((lastName == null) || lastName.isEmpty()) return null;
        if ((email == null) || email.isEmpty()) return null;
        if ((password == null) || password.isEmpty()) return null;
        if (role == null) return null;
        return userRepository.merge(new User(login, firstName, lastName, email, password, role));
    }

    public boolean logIn(@Nullable final String login, @Nullable final String password) {
        if ((login == null) || login.isEmpty()) return false;
        if ((password == null) || password.isEmpty()) return false;
        @Nullable final User user = findOne(login, login);
        if (user == null) return false;
        return userRepository.logIn(login, password);

    }

    public boolean logOut() {
        return userRepository.logOut();
    }

    public boolean updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne) {
        if ((login == null) || login.isEmpty()) return false;
        if ((oldOne == null) || oldOne.isEmpty()) return false;
        if ((newOne == null) || newOne.isEmpty()) return false;
        return userRepository.updatePassword(login, oldOne, newOne);
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
