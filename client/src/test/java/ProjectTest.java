import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;
import ru.anikanov.tm.endpoint.*;

import java.lang.Exception;

//@RunWith(CdiTestRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectTest {

    private ProjectEndPoint projectService = new ProjectEndPointService().getProjectEndPointPort();

    private UserEndPoint userEndPoint = new UserEndPointService().getUserEndPointPort();

    private SessionEndPoint sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();

    Session session;

    public void signIn() throws Exception {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void t1_persist() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.createProject(session,
                "test", "des", "20.02.2020", "20.02.2020");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }


/*
    @Test
    public void t2_merge() throws Exception {
        signIn();
        projectService.mergeProject(session, "test",
                "test", "des", "20.02.2020", "20.02.2020", "done");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("DONE", project.getStatus().toString());
        sessionService.remove(session);
    }
*/

    @Test
    public void t3_findOne() throws Exception {
        signIn();
        @Nullable final ProjectDto project = projectService.findProjectByPartOfNameProject(session, "test");
        Assert.assertNotNull(project);
        Assert.assertEquals("test", project.getName());
    }

  /*  @Test
    public void t4_findAll() throws Exception {
        signIn();
        @Nullable final List<ProjectDto> list = projectService.findAllProject(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
        sessionService.remove(session);
    }
*/
  /*  @Test
    public void t5_remove() throws Exception {
        signIn();
        projectService.removeProject(session, "test");
        @Nullable final ProjectDto project = projectService.findOneProject(session, "test");
        Assert.assertNull(project);
        sessionService.remove(session);
    }

    @Test
    public void t6_removeAll() throws Exception {
        signIn();
        projectService.removeAllProjects(session);
        @Nullable final List<ProjectDto> list = projectService.findAllProjects(session);
        Assert.assertTrue(list.isEmpty());
        sessionService.remove(session);
    }
*/
}
