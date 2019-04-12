package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskEndPoint;

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
        final TaskEndPoint endPoint = bootstrap.getTaskEndPoint();
        final String id = bootstrap.getTerminalService().nextLine();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        final String description = bootstrap.getTerminalService().nextLine();
        final String startDate = bootstrap.getTerminalService().nextLine();
        final String endDate = bootstrap.getTerminalService().nextLine();
        endPoint.updateTask(bootstrap.getCurrentSession(), id, name, description, startDate, endDate);
    }
}
