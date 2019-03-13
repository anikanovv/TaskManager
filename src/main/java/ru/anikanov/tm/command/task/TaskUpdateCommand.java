package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskUpdateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "update task";
    }

    @Override
    public String getDescription() {
        return "command to update task";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        ITaskService taskService = bootstrap.getTaskService();
        String id = scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = scanner.nextLine().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 2) {
            description = taskInfo[0];
            startDate = taskInfo[1];
            endDate = taskInfo[2];
        }
        taskService.merge(id, name, description, startDate, endDate, bootstrap.getCurrentUser());
    }
}
