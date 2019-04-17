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
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.utils.SpringJPA;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJPA.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IUserService userService;

    private User getUser() {
        return userService.findByName("volodya");
    }

    @Test
    public void test1_persist() {
        @NotNull final User user = getUser();
        @Nullable final Project project = projectService.persist("newnewnew", "des", "12.12.2012", "12.12.2012", user.getId());
        Assert.assertNotNull(project);
        Assert.assertEquals("newTest", project.getName());
    }


    @Test
    public void test2_merge() {
        @NotNull final User user = getUser();
        projectService.merge("bc416e48-a517-44ab-bed9-8fd97b9ef25c", "test", "newDes228", "12.11.2021", "21.12.2030", user.getId());
        @Nullable final Project project = projectService.findByPartOfName("test", user.getId());
        Assert.assertNotNull(project);
        Assert.assertEquals("newDes228", project.getDescription());
    }

    @Test
    public void test3_findOne() {
        @NotNull final User user = getUser();
        @Nullable final Project project = projectService.findByPartOfName("test", user.getId());
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        @NotNull final User user = getUser();
        @Nullable final List<Project> list = projectService.findAll(user.getId());
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void test5_remove() throws Exception {
        @NotNull final User user = getUser();
        projectService.remove("bc416e48-a517-44ab-bed9-8fd97b9ef25c", user.getId());
        @Nullable final Project project2 = projectService.findByPartOfName("newTest", user.getId());
        Assert.assertNull(project2);
    }

    @Test
    public void test6_removeAll() throws Exception {
        @NotNull final User user = getUser();
        projectService.removeAll(user.getId());
        @Nullable final List<Project> list = projectService.findAll(user.getId());
        Assert.assertTrue(list.isEmpty());
    }
}
