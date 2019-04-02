package ru.anikanov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.TaskMapper;
import ru.anikanov.tm.utils.SqlSessionFactory;

import java.util.Comparator;
import java.util.List;

public class TaskService extends AbstractService implements ITaskService {

    @Nullable
    public Task persist(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            @Nullable final Task task = taskMapper.findOne(taskName, userId);
            if (task != null) return null;
            if (projectId == null || projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            taskMapper.persist(newtask);
            sqlSession.commit();
            return newtask;
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            taskMapper.merge(taskId, taskName, description, dateStart, dateFinish, userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void remove(@Nullable final String taskId, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            taskMapper.remove(taskId, userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            taskMapper.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable final Task task = taskMapper.findOne(taskId, userId);
        sqlSession.close();
        if (task == null) return null;
        if (userId.equals(task.getUserId())) return task;
        else return null;
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable final Task task = taskMapper.findByPartOfName(partOfName, userId);
        sqlSession.close();
        return task;
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable final Task task = taskMapper.findByPartOfDescription(partOfDescription, userId);
        sqlSession.close();
        return task;
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable List<Task> tasks = taskMapper.findAll(userId);
        sqlSession.close();
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable List<Task> tasks = taskMapper.findAll(userId);
        sqlSession.close();
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable List<Task> tasks = taskMapper.findAll(userId);
        sqlSession.close();
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        @Nullable List<Task> tasks = taskMapper.findAll(userId);
        sqlSession.close();
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
