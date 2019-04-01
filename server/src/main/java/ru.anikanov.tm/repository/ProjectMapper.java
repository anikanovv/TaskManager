package ru.anikanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface ProjectMapper {
    @Insert("INSERT into taskmanager.app_project (id,dateBegin,dateEnd,description,name,status,user_id) VALUES(#{id}, #{startDate}, #{endDate}, #{description}, #{name}, #{status}, #{userId})")
    void persist(@NotNull final Project project);

    @Update("UPDATE taskmanager.app_project SET dateBegin = #{dateBegin}, dateEnd = #{dateEnd}, description = #{description}, name = #{name} WHERE id = #{id};")
    void merge(@Param("id") @NotNull final String projectId, @Param("name") @NotNull final String projectName, @Param("description") @NotNull final String description,
               @Param("dateBegin") @NotNull final String dateStart, @Param("dateEnd") @NotNull final String dateFinish, @Param("user_id") @NotNull final String userId);

    @Delete("DELETE FROM taskmanager.app_project WHERE name=#{name} AND user_id=#{userId};")
    void remove(@Param("name") @NotNull final String name, @Param("userId") @NotNull final String userId);

    @Delete("DELETE FROM taskmanager.app_project WHERE user_id=#{userId};")
    void removeAll(@Param("userId") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_project WHERE id=#{id} AND user_id=#{userId}")
    Project findOne(@Param("id") @NotNull final String id, @Param("userId") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_project WHERE user_id=#{id}")
    List<Project> findAll(@Param("id") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_project WHERE id=#{id} AND user_id=#{userId}")
    Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_project WHERE  user_id=#{userId} AND description LIKE #{partOfDescription}")
    Project findByPartOfDescription(@Param("partOfDescription") @NotNull final String partOfDescription, @Param("userId") @NotNull final String userId);
}
