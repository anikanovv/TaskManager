package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Session extends AbstractEntity implements Cloneable {
    private String userId;
    private Long timestamp;
//    private String signature;

    public Session() {
    }

    public Session(String userId, Long timestamp) {
        this.userId=userId;
        this.timestamp = timestamp;
    }
 /*   public void setUserId(String id){
        userId=id;
    }*/


 /*   @Override
    public Session clone() throws CloneNotSupportedException {
        return (Session) super.clone();
    }*/
}
