package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;

public class UserService extends AbstractService implements IUserService {
    @NotNull
    private IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User persist(@Nullable final String login, @Nullable final String password, @Nullable final Role role) {
        if (login.isEmpty() || (login == null)) return null;
        if (userRepository.findOne(login) == null) {
            if (password.isEmpty() || (password == null)) return null;
            if (role == null) return null;
            return userRepository.persist(new User(login, password, role));
        }
        return null;
    }

    public void merge(@Nullable final String login, @Nullable final String password, @Nullable final Role role) {
        if (login.isEmpty() || (login == null)) return;
        if (password.isEmpty() || (password == null)) return;
        if (role == null) return;
        userRepository.merge(new User(login, password, role));
    }

    public boolean logIn(@Nullable final String login, @Nullable final String password) {
        if (login.isEmpty() || (login == null)) return false;
        if (password.isEmpty() || (password == null)) return false;
        @Nullable final User user = findOne(login, login);
        if (user == null) return false;
        return userRepository.logIn(login, password);

    }

    public boolean logOut() {
        return userRepository.logOut();
    }

    public boolean updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne) {
        if (login.isEmpty() || (login == null)) return false;
        if (newOne.isEmpty() || (newOne == null)) return false;
        return userRepository.updatePassword(login, oldOne, newOne);
    }

    @Nullable
    public User findOne(@Nullable final String login, @NotNull final String userId) {
        if (login.isEmpty() || (login == null)) return null;
        return userRepository.findOne(login);
    }

    @Nullable
    public List<User> findAll(@NotNull final String userId) {
        return userRepository.findAll();
    }

    public void remove(@Nullable final String login, @NotNull final String userId) {
        if (login.isEmpty() || (login == null)) return;
        userRepository.remove(login);
    }

    public void removeAll(@NotNull final String userId) {
        userRepository.removeAll();
    }
}
