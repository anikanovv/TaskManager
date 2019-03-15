package ru.anikanov.tm.command.save;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SerializeFasterXmlJsonCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "serialize json";
    }

    @Override
    public String getDescription() {
        return "serialize all projects and tasks with json";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        Project student = new Project("asd", "asd", "12.11.1111", "12.12.1111", bootstrap.getCurrentUser());
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Выводим в json файл
            mapper.writeValue(new FileOutputStream(
                    "user.json"), student);
        } catch (Exception ex) {
            /*Logger.getLogger(SerializeFasterXmlJsonCommand.class.getName())
                    .log(Level.SEVERE, null, ex);*/
        }
    }
}
