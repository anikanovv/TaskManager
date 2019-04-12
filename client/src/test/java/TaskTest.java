import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import ru.anikanov.tm.endpoint.*;

import javax.inject.Inject;
import java.lang.Exception;
import java.util.List;

@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskTest {
    @Inject
    private TaskEndPoint taskEndPoint;
    @Inject
    private UserEndPoint userEndPoint;
    @Inject
    private SessionEndPoint sessionEndPoint;

    private Session session;

    private void logIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void test1_persist() {
        logIn();
        @Nullable final TaskDto task = taskEndPoint.createTask(session, "ee4c7769-f598-4a98-b7d5-4e0ed9601984", "test", "des", "12.12.2012", "12.12.2012");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }


    @Test
    public void test2_merge() {
        logIn();
        taskEndPoint.updateTask(session, "011b5f11-3a4e-4207-8934-91f86c533fe0", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test123");
        Assert.assertNotNull(task);
        Assert.assertEquals("newDes228", task.getDescription());
    }

    @Test
    public void test3_findOne() {
        logIn();
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        logIn();
        @Nullable final List<TaskDto> list = taskEndPoint.findAllTask(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void test5_remove() throws Exception {
        logIn();
        taskEndPoint.removeTask(session, "011b5f11-3a4e-4207-8934-91f86c533fe0");
        @Nullable final TaskDto task = taskEndPoint.findTaskByPartOfName(session, "test123");
        Assert.assertNull(task);
    }

    @Test
    public void test6_removeAll() throws Exception {
        logIn();
        taskEndPoint.removeAllTask(session);
        @Nullable final List<TaskDto> list = taskEndPoint.findAllTask(session);
        Assert.assertTrue(list.isEmpty());
    }
}

