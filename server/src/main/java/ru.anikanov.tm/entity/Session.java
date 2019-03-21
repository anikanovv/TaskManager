package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Session extends AbstractEntity implements Cloneable {
    private String userId;
    private Long timestamp;
    private String signature;

    //    private String id = UUID.randomUUID().toString();
    public Session(String userId) {
        this.userId=userId;
        this.id = UUID.randomUUID().toString();
    }
    Session(){}

    @Override
    public Session clone() throws CloneNotSupportedException {
        return (Session) super.clone();
    }
}
