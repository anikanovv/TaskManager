package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Role;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public final class User extends AbstractEntity {
    @Nullable
    private String name;
    @Nullable
    private String hashPassword;
    @Nullable
    private Role role;

    public User(@NotNull String login, @NotNull String password, @NotNull Role role) {
        this.name = login;
        this.hashPassword = password;
        this.role = role;
    }

    public String getRoleName() {
        return Objects.requireNonNull(role).displayName();
    }


}
