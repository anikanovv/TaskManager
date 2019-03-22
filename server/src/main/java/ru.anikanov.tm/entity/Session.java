package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class Session extends AbstractEntity implements Cloneable {
    @Nullable
    private String userId;
    @Nullable
    private Long timestamp;
    @Nullable
    private String signature;

    public Session(@Nullable final String userId, @Nullable final Long timestamp) {
        this.userId=userId;
        this.timestamp = timestamp;
    }

    @Override
    public Session clone() throws CloneNotSupportedException {
        return (Session) super.clone();
    }
}
