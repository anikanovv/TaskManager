import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import ru.anikanov.tm.endpoint.*;

public class UserTest {
    private UserEndPoint userEndPoint = new UserEndPointService().getUserEndPointPort();

    private SessionEndPoint sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();

    Session session;

    public void signIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

  /*  @Test
    public void test1_create() {
        signIn();
        @Nullable final UserDto user = userEndPoint.createUser(session,
                "test", "des", "12.12.2012", "12.12.2012");
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
    }


    @Test
    public void test2_update() {
        signIn();
        userEndPoint.updateUser(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final UserDto user = userEndPoint.findOneUserByName(session, "test123");
        Assert.assertNotNull(user);
        Assert.assertEquals("newDes228", user.getEmail());
    }

    @Test
    public void test3_updatePassword() {
        signIn();
        userEndPoint.updateUserPassword(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a", "test123", "newDes228", "12.11.2021", "21.12.2030");
        @Nullable final UserDto user = userEndPoint.findOneUserByName(session, "test123");
        Assert.assertNotNull(user);
        Assert.assertNotNull(userEndPoint.logIn());
    }

    @Test
    public void test4_findOne() {
        signIn();
        @Nullable final UserDto user = userEndPoint.findOneUserByName(session, "test");
        Assert.assertNotNull(user);
        Assert.assertEquals("test3", user.getLogin());
    }

    @Test
    public void test5_findAll() throws Exception {
        signIn();
        @Nullable final List<UserDto> list = userEndPoint.findAllUser(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("test", list.get(0).getName());
    }

    @Test
    public void test6_remove() throws Exception {
        signIn();
        userEndPoint.removeUser(session, "21f935fc-f0f9-4a30-a171-b81095ff2a2a");
        @Nullable final UserDto user = userEndPoint.findOneUserByName(session, "test123");
        Assert.assertNull(user);
    }

    @Test
    public void test7_removeAll() throws Exception {
        signIn();
        userEndPoint.removeAllUser(session);
        @Nullable final List<UserDto> list = userEndPoint.findAllUser(session);
        Assert.assertTrue(list.isEmpty());
    }*/
}

