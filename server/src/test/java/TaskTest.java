import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.SpringJPA;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJPA.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    private User getUser() {
        return userService.findByName("volodya");
    }

    @Test
    public void test1_persist() {
        @NotNull final User user = getUser();
        @Nullable final Task task = taskService.persist(user.getId(), "ee4c7769-f598-4a98-b7d5-4e0ed9601984", "test", "des", "12.12.2012", "12.12.2012");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }


    @Test
    public void test2_merge() {
        @NotNull final User user = getUser();
        taskService.merge(user.getId(), "011b5f11-3a4e-4207-8934-91f86c533fe0", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final Task task = taskService.findByPartOfDescription(user.getId(), "test123");
        Assert.assertNotNull(task);
        Assert.assertEquals("newDes228", task.getDescription());
    }

    @Test
    public void test3_findOne() {
        @NotNull final User user = getUser();
        @Nullable final Task task = taskService.findByPartOfName(user.getId(), "test");
        Assert.assertNotNull(task);
        Assert.assertEquals("test", task.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        @NotNull final User user = getUser();
        @Nullable final List<Task> list = taskService.findAll(user.getId());
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void test5_remove() throws Exception {
        @NotNull final User user = getUser();
        taskService.remove(user.getId(), "011b5f11-3a4e-4207-8934-91f86c533fe0");
        @Nullable final Task task = taskService.findByPartOfName(user.getId(), "test123");
        Assert.assertNull(task);
    }

    @Test
    public void test6_removeAll() throws Exception {
        @NotNull final User user = getUser();
        taskService.removeAll(user.getId());
        @Nullable final List<Task> list = taskService.findAll(user.getId());
        Assert.assertTrue(list.isEmpty());
    }
}

