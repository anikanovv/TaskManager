package ru.anikanov.tm.service;

import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;

import java.text.ParseException;
import java.util.List;

public class TaskService extends AbstractService implements ITaskService {
    private IProjectRepository projectRepository;
    private ITaskRepository taskRepository;
    private IUserRepository userRepository;

    public TaskService(IProjectRepository pr, ITaskRepository tr, IUserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    public AbstractEntity findOne(String taskId, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        return taskRepository.findOne(taskId);
    }

    public AbstractEntity persist(String projectId, String taskName, String description, String dateStart, String dateFinish, String userId) {
        if (taskName.isEmpty() || (taskName == null)) return null;
        Task task = (Task) taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty() || (projectId == null)) return null;
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            if (userId.isEmpty() || (userId == null)) return null;
        }
        try {
            Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            return taskRepository.persist(newtask);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void merge(String taskId, String taskName, String description, String dateStart, String dateFinish, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return;
        Task task = (Task) taskRepository.findOne(taskId);
        User user = (User) userRepository.findOne(userId);
        if ((!userId.equals(task.getUserId())) || (!user.getRole().equals("admin"))) return;
        if (task != null) {
            if (taskName.isEmpty() || (taskName == null)) return;
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {

            taskRepository.merge(new Task(task.getProjectId(), taskName, description, dateStart, dateFinish, userId));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void remove(String taskId, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return;
        Task task = (Task) taskRepository.findOne(taskId);
        User user = (User) userRepository.findOne(userId);
        if ((!userId.equals(task.getUserId())) || (!user.getRole().equals("admin")))
            return;
        taskRepository.remove(taskId);
    }

    public void removeAll(String userId) {
        User user = (User) userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return;
        taskRepository.removeAll();
    }

    public List<AbstractEntity> findAll(String userId) {
        User user = (User) userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return null;
        return taskRepository.findAll();
    }
}
