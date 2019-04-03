package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Role;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User extends AbstractEntity {
    @NotNull
    @Id
    private String id = super.getId();
    @Nullable
    @Column(name = "login")
    private String name;
    @Nullable
    @Column(name = "passwordHash")
    private String hashPassword;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public User(@NotNull final String login, @NotNull final String firstName, @NotNull final String lastName, @NotNull final String email,
                @NotNull final String password, @NotNull final Role role) {
        this.name = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashPassword = password;
    }

    public String getRoleName() {
        return Objects.requireNonNull(role).displayName();
    }


}
