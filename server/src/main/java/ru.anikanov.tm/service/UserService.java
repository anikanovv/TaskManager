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
        if ((login == null) || login.isEmpty()) return null;
        if (userRepository.findOne(login) == null) {
            if ((password == null) || password.isEmpty()) return null;
            if (role == null) return null;
            return userRepository.persist(new User(login, password, role));
        }
        return null;
    }

    public void merge(@Nullable final String login, @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return;
        if ((password == null) || password.isEmpty()) return;
        if (role == null) return;
        userRepository.merge(new User(login, password, role));
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
}
