package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session extends AbstractEntity{
    private String userId;
    private long timestamp;
    private String signature;

    Session(String userId,String signature){
        this.userId=userId;
        this.timestamp=System.currentTimeMillis();
        this.signature=signature;
    }
    Session(){}
}
