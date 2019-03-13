package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskCreateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "create task";
    }

    @Override
    public String getDescription() {
        return "command to create task";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        ITaskService taskService = bootstrap.getTaskService();
        String userId = bootstrap.getCurrentUser();
        String name = scanner.nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = scanner.next().split(";");
        String projectId = null;
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 3) {
            projectId = taskInfo[0];
            description = taskInfo[1];
            startDate = taskInfo[2];
            endDate = taskInfo[3];
        }
        taskService.persist(projectId, name, description, startDate, endDate, userId);
    }
}
