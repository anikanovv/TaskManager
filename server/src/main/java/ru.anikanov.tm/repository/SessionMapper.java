package ru.anikanov.tm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.anikanov.tm.entity.Session;

public interface SessionMapper {
    /*   @Results({
               @Result(property = "id", column = "id"),
               @Result(property = "signature", column = "name"),
               @Result(property = "timestamp", column = "district"),
               @Result(property = "userId", column = "user_id")
       })*/
    @Insert("INSERT into taskmanager.app_session (id,signature,timestamp,user_id) VALUES(#{id}, #{signature}, #{timestamp}, #{userId})")
    void create(Session session);

    @Results({
            @Result(property = "userId", column = "user_id")
    })
    @Select("SELECT * FROM taskmanager.app_session WHERE id= #{sessionId}")
    Session findOne(String sessionId);
}
