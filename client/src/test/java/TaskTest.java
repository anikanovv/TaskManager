import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anikanov.tm.endpoint.*;
import ru.anikanov.tm.utils.SpringConfig;

import java.lang.Exception;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskTest {
    @Autowired
    private TaskEndPoint taskEndPoint;
    @Autowired
    private UserEndPoint userEndPoint;
    @Autowired
    private SessionEndPoint sessionEndPoint;

    private Session session;

    public void signIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("admin", "admin");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void test1_persist() {
        signIn();
        @Nullable final TaskDto task = taskEndPoint.createTask(session, "test123", "des", "12.12.2012", "12.12.2012", "ee4c7769-f598-4a98-b7d5-4e0ed9601984");
        Assert.assertNotNull(task);
        Assert.assertEquals("test123", task.getName());
    }


    @Test
    public void test2_merge() {
        signIn();
        taskEndPoint.updateTask(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test123");
        Assert.assertNotNull(task);
        Assert.assertEquals("newDes228", task.getDescription());
    }

    @Test
    public void test3_findOne() {
        signIn();
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test3", task.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        signIn();
        @Nullable final List<TaskDto> list = taskEndPoint.findAllTask(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void test5_remove() throws Exception {
        signIn();
        taskEndPoint.removeTask(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a");
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test123");
        Assert.assertNull(task);
    }

    @Test
    public void test6_removeAll() throws Exception {
        signIn();
        taskEndPoint.removeAllTask(session);
        @Nullable final List<TaskDto> list = taskEndPoint.findAllTask(session);
        Assert.assertTrue(list.isEmpty());
    }
}

