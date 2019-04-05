package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "app_session")
public class Session extends AbstractEntity implements Cloneable {

    @Nullable
    @Column(name = "user_id")
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
