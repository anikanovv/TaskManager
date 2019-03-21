package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Role;

@Getter
@Setter
public class User extends AbstractEntity {
    @Nullable
    private String name;
    @NotNull
    private String hashPassword;
    @NotNull
    private Role role;

    public User(@NotNull String login, @NotNull String password, @NotNull Role role) {
        this.name = login;
        this.hashPassword = password;
        this.role = role;
    }

    public String getRoleName() {
        return role.displayName();
    }


}
