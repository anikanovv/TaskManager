package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.utils.PasswordHashUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;

public class UserService implements IUserService {
    private EntityManagerFactory factory;

    public UserService(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Nullable
    public User persist(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                        @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            if (userRepository.findOne(login) != null) return null;
            if ((firstName == null) || firstName.isEmpty()) return null;
            if ((lastName == null) || lastName.isEmpty()) return null;
            if ((email == null) || email.isEmpty()) return null;
            if ((password == null) || password.isEmpty()) return null;
            if (role == null) return null;
            @Nullable final User user = new User(login, firstName, lastName, email, password, role);
            entityManager.getTransaction().begin();
            userRepository.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
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
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            @Nullable User user = findOne(id);
            if (user == null) user = new User();
            user.setRole(role);
            user.setHashPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setName(login);
            entityManager.getTransaction().begin();
            userRepository.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public User logIn(@Nullable final String login, @Nullable final String password) {
        if ((login == null) || login.isEmpty()) return null;
        if ((password == null) || password.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            @Nullable final User user = userRepository.logIn(login, password);
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne) {
        if ((login == null) || login.isEmpty()) return;
        if ((oldOne == null) || oldOne.isEmpty()) return;
        if ((newOne == null) || newOne.isEmpty()) return;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            @Nullable final User user = userRepository.logIn(login, PasswordHashUtil.md5(oldOne));
            if (user == null) return;
            entityManager.getTransaction().begin();
            user.setHashPassword(PasswordHashUtil.md5(newOne));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(@NotNull final String userId) {
        if (userId.isEmpty()) return;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            entityManager.getTransaction().begin();
            @Nullable final User user = userRepository.findOne(userId);
            if (user == null) return;
            userRepository.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAll() {
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final UserRepository userRepository = new UserRepository(entityManager);
            entityManager.getTransaction().begin();
            userRepository.removeAll();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public User findOne(@NotNull final String userId) {
        if (userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.findOne(userId);
    }

    @Nullable
    public List<User> findAll() {
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.findAll();
    }

    public boolean checkadmin(@NotNull final Session session) {
        if (session.getUserId() == null) return false;
        return Objects.requireNonNull(Objects.requireNonNull(findOne(session.getUserId())).getRole()).equals(Role.ADMIN);
    }

    public User findByName(@NotNull final String login) {
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.findByName(login);
    }

    public User getCurrentUser(@NotNull final Session session) {
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        UserRepository userRepository = new UserRepository(entityManager);
        return userRepository.findOne(Objects.requireNonNull(session.getUserId()));
    }
}
