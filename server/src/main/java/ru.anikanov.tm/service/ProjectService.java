package ru.anikanov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectMapper;
import ru.anikanov.tm.repository.TaskMapper;
import ru.anikanov.tm.utils.SqlSessionFactory;

import java.util.Comparator;
import java.util.List;

public class ProjectService implements IProjectService {
    @Nullable
    public Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                           @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        if (userId == null || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        try {
            @Nullable final Project project = projectMapper.findOne(projectName, userId);
            if (project == null) {
                if ((description == null) || description.isEmpty()) return null;
                if ((dateStart == null) || dateStart.isEmpty()) return null;
                if ((dateFinish == null) || dateFinish.isEmpty()) return null;
                @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
                projectMapper.persist(newproject);
                sqlSession.commit();
                return newproject;
            }
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }

        return null;
    }

    public void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                      @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        try {
            @Nullable final Project p = new Project(projectName, description, dateStart, dateFinish, userId);
            projectMapper.merge(p.getId(), projectName, description, dateStart, dateFinish, userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }


    }

    public void remove(@Nullable final String projectName, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            @Nullable final Project project = projectMapper.findByPartOfName(projectName, userId);
            if (project == null) return;
            taskMapper.removeWholeProject(project.getId(), userId);
            projectMapper.remove(projectName, userId);
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
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @NotNull final TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
        try {
            taskMapper.removeAll(userId);
            projectMapper.removeAll(userId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Nullable
    public List<Project> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable List<Project> projects = null;
        try {
            projects = projectMapper.findAll(userId);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return projects;
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((projectId == null) || projectId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable Project project = null;
        try {
            project = projectMapper.findOne(projectId, userId);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Nullable
    public List<Project> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable List<Project> projects = null;
        try {
            projects = projectMapper.findAll(userId);
            if (projects == null) return null;
            projects.sort(Comparator.comparing(Project::getStartDate));
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable List<Project> projects = null;
        try {
            projects = projectMapper.findAll(userId);
            if (projects == null) return null;
            projects.sort(Comparator.comparing(Project::getEndDate));
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable List<Project> projects = null;
        try {
            projects = projectMapper.findAll(userId);
            if (projects == null) return null;
            projects.sort(Comparator.comparing(Project::getStatus));
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable Project project = null;
        try {
            project = projectMapper.findByPartOfName(partOfName, userId);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return project;
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        @NotNull final ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
        @Nullable Project project = null;
        try {
            project = projectMapper.findByPartOfDescription(partOfDescription, userId);
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return project;
    }
}
