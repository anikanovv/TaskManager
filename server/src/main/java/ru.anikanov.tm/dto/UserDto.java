package ru.anikanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Role;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
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
}
