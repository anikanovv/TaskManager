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
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @Nullable
    private Role role;

    public User(@NotNull final String login, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email,
                @NotNull final String password, @NotNull final Role role) {
        this.name = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashPassword = password;
        this.role = role;
    }

    public String getRoleName() {
        return Objects.requireNonNull(role).displayName();
    }


}
