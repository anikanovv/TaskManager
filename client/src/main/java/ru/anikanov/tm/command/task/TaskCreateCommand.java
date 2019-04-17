package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskEndPoint;

import java.util.Objects;

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
        final TaskEndPoint endPoint = bootstrap.getTaskEndPoint();
        final String userId = Objects.requireNonNull(bootstrap.getCurrentSession()).getUserId();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        final String projectId = bootstrap.getTerminalService().nextLine();
        final String description = bootstrap.getTerminalService().nextLine();
        final String startDate = bootstrap.getTerminalService().nextLine();
        final String endDate = bootstrap.getTerminalService().nextLine();

        endPoint.createTask(bootstrap.getCurrentSession(), projectId, name, description, startDate, endDate);
    }
}
