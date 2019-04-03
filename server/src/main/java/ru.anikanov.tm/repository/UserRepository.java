package ru.anikanov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
public class UserRepository extends AbstractRepository implements IUserRepository {
    @Getter
    private EntityManager entityManager;

    public UserRepository(@Nullable final EntityManager entityManager) {
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
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name = ?1")
                .setParameter(1, login)
                .getSingleResult();
    }

    public User logIn(@NotNull final String login, @NotNull final String password) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.name = ?1 AND u.hashPassword = ?2")
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
    }
}
