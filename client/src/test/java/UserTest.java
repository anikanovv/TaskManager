import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.anikanov.tm.endpoint.*;

import javax.inject.Inject;
import java.lang.Exception;
import java.util.List;

@RunWith(CdiTestRunner.class)
public class UserTest {
    @Inject
    private UserEndPoint userEndPoint;
    @Inject
    private SessionEndPoint sessionEndPoint;

    private Session session;

    public void logIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void test1_create() {
        logIn();
        @Nullable final UserDto user = userEndPoint.createUser("login", "pass", "des", "12.12.2012", "12.12.2012", Role.USER);
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
    }


    @Test
    public void test2_update() {
        logIn();
        userEndPoint.updateUser("login", "my", "wife", "is", "male", Role.USER, "18fabae5-7117-4b63-ac77-799c0b3908fd");
        @Nullable final UserDto user = userEndPoint.findOneUserByName("login");
        Assert.assertNotNull(user);
        Assert.assertEquals("wife", user.getEmail());
    }

    @Test
    public void test3_updatePassword() {
        logIn();
        userEndPoint.updateUserPassword("user", "user", "user228");
        @Nullable final UserDto user = userEndPoint.logIn("login", "user228");
        Assert.assertNotNull(user);
    }

    @Test
    public void test4_findOne() {
        logIn();
        @Nullable final UserDto user = userEndPoint.findOneUser(session, "18fabae5-7117-4b63-ac77-799c0b3908fd");
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getLogin());
    }

    @Test
    public void test5_findAll() throws Exception {
        logIn();
        @Nullable final List<UserDto> list = userEndPoint.findAllUser(session);
        Assert.assertNotNull(list);
        Assert.assertEquals("user", list.get(0).getLogin());
    }

    @Test
    public void test6_remove() throws Exception {
        logIn();
        userEndPoint.removeUser(session, "18fabae5-7117-4b63-ac77-799c0b3908fd");
        @Nullable final UserDto user = userEndPoint.findOneUserByName("test");
        Assert.assertNull(user);
    }

    @Test
    public void test7_removeAll() throws Exception {
        logIn();
        userEndPoint.removeAllUser(session);
        @Nullable final List<UserDto> list = userEndPoint.findAllUser(session);
        Assert.assertTrue(list.isEmpty());
    }
}

