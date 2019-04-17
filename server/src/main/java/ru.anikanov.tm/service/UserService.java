package ru.anikanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.utils.PasswordHashUtil;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@NoArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Nullable
    public User persist(@NotNull final String login, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email,
                        @NotNull final String password, @NotNull final Role role) {
        if (login.isEmpty()) return null;
        if (password.isEmpty()) return null;
        @NotNull final User user = new User(login, firstName, lastName, email, password, role);
        userRepository.save(user);
            return user;
    }

    public void merge(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                      @Nullable final String password, @Nullable final Role role, @NotNull final String id) {
        if (id.isEmpty()) return;
        if ((login == null) || login.isEmpty()) return;
        if ((firstName == null) || firstName.isEmpty()) return;
        if ((lastName == null) || lastName.isEmpty()) return;
        if ((email == null) || email.isEmpty()) return;
        if ((password == null) || password.isEmpty()) return;
        if (role == null) return;
            @Nullable User user = findOne(id);
            if (user == null) user = new User();
            user.setRole(role);
            user.setHashPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setName(login);
        userRepository.save(user);
    }

    public User logIn(@Nullable final String login, @Nullable final String password) {
        if ((login == null) || login.isEmpty()) return null;
        if ((password == null) || password.isEmpty()) return null;
        @Nullable final User user = userRepository.findByNameAndHashPassword(login, PasswordHashUtil.md5(password));
            return user;
    }

    public void updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne) {
        if ((login == null) || login.isEmpty()) return;
        if ((oldOne == null) || oldOne.isEmpty()) return;
        if ((newOne == null) || newOne.isEmpty()) return;
        @Nullable final User user = userRepository.findByNameAndHashPassword(login, PasswordHashUtil.md5(oldOne));
            if (user == null) return;
            user.setHashPassword(PasswordHashUtil.md5(newOne));
        userRepository.save(user);
    }

    public void remove(@NotNull final String userId) {
        if (userId.isEmpty()) return;
        @Nullable final User user = userRepository.findById(userId).orElse(null);
            if (user == null) return;
        userRepository.delete(user);
    }

    public void removeAll() {
        userRepository.deleteAll();
    }

    @Nullable
    public User findOne(@NotNull final String userId) {
        if (userId.isEmpty()) return null;
        return userRepository.findById(userId).orElse(null);
    }

    @Nullable
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean checkadmin(@NotNull final Session session) {
        if (session.getUserId() == null) return false;
        return Objects.requireNonNull(Objects.requireNonNull(findOne(session.getUserId())).getRole()).equals(Role.ADMIN);
    }

    public User findByName(@NotNull final String login) {
        return userRepository.findByName(login);
    }

    public User getCurrentUser(@NotNull final Session session) {
        return userRepository.findById(session.getUserId()).orElse(null);
    }
}
