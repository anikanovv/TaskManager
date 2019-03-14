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
        final ITaskService taskService = bootstrap.getTaskService();
        final String userId = bootstrap.getCurrentUser();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        final String projectId = bootstrap.getTerminalService().nextLine();
        final String description = bootstrap.getTerminalService().nextLine();
        final String startDate = bootstrap.getTerminalService().nextLine();
        final String endDate = bootstrap.getTerminalService().nextLine();

        taskService.persist(projectId, name, description, startDate, endDate, userId);
    }
}
