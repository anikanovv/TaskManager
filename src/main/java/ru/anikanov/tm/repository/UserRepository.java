package ru.anikanov.tm.repository;

import lombok.Getter;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AbstractRepository implements IUserRepository {
    @Getter
    private Map<String, User> userMap = new LinkedHashMap<>();

    public User persist(User user) {
        return userMap.put(user.getName(), user);
    }

    public void merge(User u) {
        User user = userMap.get(u.getName());
        user.setHashPassword(u.getHashPassword());
        user.setRole(u.getRole());
    }

    public boolean logIn(String login, String password) {
        User user = findOne(login);
        if (user.getHashPassword().equals(password)) {
            return true;
        } else return false;
    }

    public boolean logOut() {
        return true;
    }

    public boolean updatePassword(String login, String oldOne, String newOne) {
        User user = userMap.get(login);
        if (user.getHashPassword().equals(oldOne)) {
            user.setHashPassword(newOne);
            return true;
        } else return false;

    }

    public User findOne(String login) {
        return userMap.get(login);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userMap.forEach((k, v) -> users.add(v));
        return users;
    }


    public void remove(String login) {
        userMap.remove(login);
    }

    public void removeAll() {
        userMap.clear();
    }

}
