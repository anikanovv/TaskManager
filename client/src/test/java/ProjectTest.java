import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ru.anikanov.tm.endpoint.*;

import java.lang.Exception;
import java.util.List;

//@RunWith(CdiTestRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    private ProjectEndPoint projectService = new ProjectEndPointService().getProjectEndPointPort();

    private UserEndPoint userEndPoint = new UserEndPointService().getUserEndPointPort();

    private SessionEndPoint sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();

    Session session;

    public void signIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void test1_persist() {
        signIn();
        @Nullable final ProjectDto project = projectService.createProject(session,
                "test", "des", "12.12.2012", "12.12.2012");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }


    @Test
    public void test2_merge() {
        signIn();
        projectService.updateProject(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test123");
        Assert.assertNotNull(project);
        Assert.assertEquals("newDes228", project.getDescription());
    }

    @Test
    public void test3_findOne() {
        signIn();
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test3", project.getName());
    }

    @Test
    public void test4_findAll() throws Exception {
        signIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProject(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void test5_remove() throws Exception {
        signIn();
        projectService.removeProject(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a");
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test123");
        Assert.assertNull(project);
    }

    @Test
    public void test6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProject(session);
        @Nullable final List<ProjectDto> list = projectService.findAllProject(session);
        Assert.assertTrue(list.isEmpty());
    }
}
