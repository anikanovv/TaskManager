package ru.anikanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    @NotNull
    private String id;
    @Nullable
    private String login;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @Nullable
    private Role role;

    public UserDto(@NotNull final User user) {
        this.id = user.getId();
        this.login = user.getName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
