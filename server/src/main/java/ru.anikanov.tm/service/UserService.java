package ru.anikanov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.UserMapper;
import ru.anikanov.tm.utils.PasswordHashUtil;
import ru.anikanov.tm.utils.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

public class UserService implements IUserService {

    @Nullable
    public User persist(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
                        @Nullable final String password, @Nullable final Role role) {
        if ((login == null) || login.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            if (userMapper.findOne(login) == null) {
                if ((firstName == null) || firstName.isEmpty()) return null;
                if ((lastName == null) || lastName.isEmpty()) return null;
                if ((email == null) || email.isEmpty()) return null;
                if ((password == null) || password.isEmpty()) return null;
                if (role == null) return null;
                @Nullable final User user = new User(login, firstName, lastName, email, password, role);

                userMapper.persist(user);
                sqlSession.commit();
                return user;
            }
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
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
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            userMapper.merge(new User(login, firstName, lastName, email, password, role));
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
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
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            userMapper.updatePassword(login, oldOne, newOne);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void remove(@Nullable final String login, @NotNull final String userId) {
        if ((login == null) || login.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            userMapper.remove(login);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void removeAll(@NotNull final String userId) {
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            userMapper.removeAll();
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    public User findOne(@Nullable final String login, @NotNull final String userId) {
        if ((login == null) || login.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        @Nullable User user = null;
        try {
            user = userMapper.findOne(login);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    @Nullable
    public List<User> findAll(@NotNull final String userId) {
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        @Nullable List<User> users = null;
        try {
            users = userMapper.findAll();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return users;
    }

    public boolean checkadmin(@NotNull final Session session) {
        if (session.getUserId() == null) return false;
        return Objects.requireNonNull(Objects.requireNonNull(findOne(session.getUserId(), session.getUserId())).getRole()).equals(Role.ADMIN);
    }

    public User findByName(@NotNull final String name) {
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        @Nullable User user = null;
        try {
            user = userMapper.findByName(name);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    public User getCurrentUser(@NotNull final Session session) {
        SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        @Nullable User user = null;
        try {
            user = userMapper.findOne(Objects.requireNonNull(session.getUserId()));
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        if (user == null) return null;
        return user;
    }
}
