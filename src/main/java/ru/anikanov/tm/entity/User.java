package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import ru.anikanov.tm.enumeration.Role;

@Getter
@Setter
public class User extends AbstractEntity {
    private String name;
    private String hashPassword;
    private Role role;

    public User(String login, String password, Role role) {
        this.name = login;
        this.hashPassword = password;
        this.role = role;
    }

    public String getRoleName() {
        return role.displayName();
    }


}
