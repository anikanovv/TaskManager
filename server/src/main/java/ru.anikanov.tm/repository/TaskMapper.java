package ru.anikanov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface TaskMapper {
    @Insert("INSERT into taskmanager.app_task (id,dateBegin,dateEnd,description,name,status,project_id,user_id) VALUES(#{id}, #{startDate}, " +
            "#{endDate}, #{taskDescription}, #{taskName}, #{status},#{projectId}, #{userId})")
    void persist(@NotNull final Task task);

    @Update("UPDATE taskmanager.app_task SET dateBegin = #{dateBegin}, dateEnd = #{dateEnd}, description = #{description}, name = #{name} WHERE id = #{id};")
    void merge(@Param("id") @NotNull final String taskId, @Param("name") @NotNull final String taskName, @Param("description") @NotNull final String description,
               @Param("dateBegin") @NotNull final String dateStart, @Param("dateEnd") @NotNull final String dateFinish, @Param("user_id") @NotNull final String userId);

    @Delete("DELETE FROM taskmanager.app_task WHERE name=#{name} AND user_id=#{userId};")
    void remove(@Param("name") @NotNull final String name, @Param("userId") @NotNull final String userId);

    @Delete("DELETE FROM taskmanager.app_task WHERE user_id=#{userId};")
    void removeAll(@Param("userId") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "taskDescription", column = "description"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_task WHERE id=#{id} AND user_id=#{userId}")
    Task findOne(@Param("id") @NotNull final String id, @Param("userId") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "taskDescription", column = "description"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_task WHERE user_id=#{id}")
    List<Task> findAll(@Param("id") @NotNull final String userId);

    @Delete("DELETE FROM taskmanager.app_task WHERE project_id=#{projectId} AND user_id=#{userId};")
    void removeWholeProject(@Param("projectId") @NotNull final String projectId, @Param("userId") @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "taskDescription", column = "description"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_task WHERE id=#{id} AND user_id=#{userId}")
    Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @Nullable
    @Results({
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "taskDescription", column = "description"),
            @Result(property = "startDate", column = "dateBegin"),
            @Result(property = "endDate", column = "dateEnd"),
            @Result(property = "userId", column = "user_id"),
    })
    @Select("SELECT * FROM taskmanager.app_task WHERE  user_id=#{userId} AND description LIKE #{partOfDescription}")
    Task findByPartOfDescription(@Param("partOfDescription") @NotNull final String partOfDescription, @Param("userId") @NotNull final String userId);


}
