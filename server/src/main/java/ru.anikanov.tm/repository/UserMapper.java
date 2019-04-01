package ru.anikanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT into taskmanager.app_user (id,email,firstName,lastName,login,passwordHash,role) VALUES(#{id}, #{email}, " +
            "#{firstName}, #{lastName}, #{name}, #{hashPassword},#{role})")
    void persist(@NotNull final User user);

    @Update("UPDATE taskmanager.app_user SET firstName = #{firstName}, lastName = #{lastName}, email = #{email},}, role = #{role} WHERE id = #{id};")
    void merge(@NotNull User user);

    @Update("UPDATE taskmanager.app_user SET passwordHash = #{newOne} WHERE login = #{login};")
    void updatePassword(@Param("login") @NotNull final String login, @Param("oldOne") @NotNull final String oldOne, @Param("newOne") @NotNull final String newOne);

    @Delete("DELETE FROM taskmanager.app_user WHERE login=#{login};")
    void remove(@Param("login") @NotNull final String login);

    @Delete("DELETE FROM taskmanager.app_user ;")
    void removeAll();

    @Results({
            @Result(property = "name", column = "login"),
            @Result(property = "hashPassword", column = "passwordHash"),
    })
    @Select("SELECT * FROM taskmanager.app_user WHERE login=#{login} ")
    @Nullable
    User findOne(@Param("login") @NotNull final String login);

    @Nullable
    @Results({
            @Result(property = "name", column = "login"),
            @Result(property = "hashPassword", column = "passwordHash"),
    })
    @Select("SELECT * FROM taskmanager.app_user")
    List<User> findAll();

    @Results({
            @Result(property = "name", column = "login"),
            @Result(property = "hashPassword", column = "passwordHash"),
    })
    @Select("SELECT * FROM taskmanager.app_user WHERE id=#{id}")
    User findByName(@Param("id") @NotNull final String id);
}
