package ru.anikanov.tm.command.load;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

import java.io.*;

public class LoadFasterXmlCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "load fastxml";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() throws Exception {
        File file = new File("fastxsml.xml");
        XmlMapper xmlMapper = new XmlMapper();
//        String xml = inputStreamToString(new FileInputStream(file));
        Project value = xmlMapper.readValue(file, Project.class);
        System.out.println(value.getName());

    }

    public String inputStreamToString(InputStream is) throws Exception {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
