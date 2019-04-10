package ru.anikanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.utils.PasswordHashUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
@NoArgsConstructor
public class UserRepository extends AbstractRepository implements IUserRepository {
    private EntityManager entityManager;

    @Inject
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @NotNull
    public User persist(@NotNull final User user) {
        entityManager.persist(user);
        return user;
    }

    public User merge(@NotNull final User u) {
        entityManager.merge(u);
        return u;
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }

    @Nullable
    public List<User> findAll() {
        return entityManager.createQuery("Select user from User user")
                .getResultList();
    }

    public void remove(@NotNull final User user) {
        entityManager.remove(user);
    }

    public void removeAll() {
        entityManager.createQuery("Delete user from User user").executeUpdate();
    }

    public User findByName(@NotNull final String login) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name = :login")
                .setParameter("login", login)
                .getSingleResult();
    }

    public User logIn(@NotNull final String login, @NotNull final String password) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name = :login AND u.hashPassword = :password")
                .setParameter("login", login)
                .setParameter("password", PasswordHashUtil.md5(password))
                .getSingleResult();
    }
}
