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
public class ProjectTest {
    @Inject
    private ProjectEndPoint projectService;
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
        @Nullable final ProjectDto project = projectService.createProject(session,
                "newTest", "des", "12.12.2012", "12.12.2012");
        Assert.assertNotNull(project);
        Assert.assertEquals("newTest", project.getName());
    }


    @Test
    public void test2_merge() {
        logIn();
        projectService.updateProject(session, "bc416e48-a517-44ab-bed9-8fd97b9ef25c", "test", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("newDes228", project.getDescription());
    }

    @Test
    public void test3_findOne() {
        logIn();
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        logIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProject(session);
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void test5_remove() throws Exception {
        logIn();
        projectService.removeProject(session, "bc416e48-a517-44ab-bed9-8fd97b9ef25c");
        @Nullable final ProjectDto project2 = projectService.findProjectByPartOfNameProject(session, "newTest");
        Assert.assertNull(project2);
    }

    @Test
    public void test6_removeAll() throws Exception {
        logIn();
        projectService.removeAllProject(session);
        @Nullable final List<ProjectDto> list = projectService.findAllProject(session);
        Assert.assertTrue(list.isEmpty());
    }
}
