package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskUpdateCommand extends AbstractCommand {

    public TaskUpdateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final ITaskService taskService = bootstrap.getTaskService();
        final String id = bootstrap.getTerminlService().nextLine();
        final String name = bootstrap.getTerminlService().nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        final String description = bootstrap.getTerminlService().nextLine();
        final String startDate = bootstrap.getTerminlService().nextLine();
        final String endDate = bootstrap.getTerminlService().nextLine();
        taskService.merge(id, name, description, startDate, endDate, bootstrap.getCurrentUser());
    }
}
