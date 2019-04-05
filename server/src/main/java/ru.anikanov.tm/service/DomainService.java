package ru.anikanov.tm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IDomainService;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Domain;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.Task;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class DomainService implements IDomainService {
    @Inject
    private IUserService userService;
    @Inject
    private IProjectService projectService;
    @Inject
    private ITaskService taskService;

    public void fasterJson(@NotNull final Session session) {
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        @NotNull final FileOutputStream fos;
        try {
            fos = new FileOutputStream(userService.getCurrentUser(session).getName() + "fasterjson.json");
            @NotNull final List<Project> projects = Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId())));
            @Nullable final List<Task> tasks = taskService.findAll(Objects.requireNonNull(session.getUserId()));
            @NotNull final Domain domain = new Domain();
            domain.setProjects(projects);
            domain.setTasks(tasks);
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(fos, domain);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void fasterXml(@NotNull final Session session) {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final FileOutputStream file;
        try {
            file = new FileOutputStream(userService.getCurrentUser(session).getName() + "fasterxml" + ".xml");
            @Nullable final List<Project> projects = projectService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final List<Task> tasks = taskService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final Domain domain = new Domain();
            domain.setProjects(projects);
            domain.setTasks(tasks);
            try {
                xmlMapper.writerWithDefaultPrettyPrinter().writeValue(file, domain);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void jaxbJson(@NotNull final Session session) {
        @NotNull final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Domain.class);
            @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("eclipselink.media-type", "application/json");
            @NotNull final FileWriter file = new FileWriter(userService.getCurrentUser(session).getName() + "jaxbjson.json");
            @Nullable final List<Project> projects = projectService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final List<Task> tasks = taskService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final Domain domain = new Domain();
            domain.setProjects(projects);
            domain.setTasks(tasks);
            marshaller.marshal(domain, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void jaxbXml(@NotNull final Session session) {
        @NotNull final JAXBContext context;
        try {
            context = JAXBContext.newInstance(Project.class);
            @NotNull final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            @NotNull final File file = new File(userService.getCurrentUser(session) + "jaxbxml.xml");
            @Nullable final List<Project> projects = projectService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final List<Task> tasks = taskService.findAll(session.getUserId());
            @Nullable final Domain domain = new Domain();
            domain.setProjects(projects);
            domain.setTasks(tasks);
            marshaller.marshal(domain, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void standartSerialize(@NotNull final Session session) {
        @NotNull final ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(userService.getCurrentUser(session).getName() + "serialize.dat"));
            @Nullable final List<Project> projects = projectService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final List<Task> tasks = taskService.findAll(Objects.requireNonNull(session.getUserId()));
            @Nullable final Domain domain = new Domain();
            domain.setProjects(projects);
            domain.setTasks(tasks);
            oos.writeObject(domain);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
