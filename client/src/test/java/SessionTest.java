import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.anikanov.tm.endpoint.Session;
import ru.anikanov.tm.endpoint.SessionEndPoint;
import ru.anikanov.tm.endpoint.UserDto;
import ru.anikanov.tm.endpoint.UserEndPoint;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class SessionTest {
    @Inject
    private SessionEndPoint sessionEndPoint;
    @Inject
    private UserEndPoint userEndPoint;

    private Session session;

    public void logIn() {
        @Nullable final UserDto userDto = userEndPoint.logIn("user", "user");
        Assert.assertNotNull(userDto);
        session = sessionEndPoint.createSession(userDto.getId());
        Assert.assertNotNull(session);
    }

    @Test
    public void test1_create() {
        @Nullable final Session session = sessionEndPoint.createSession("18fabae5-7117-4b63-ac77-799c0b3908fd");
        Assert.assertNotNull(session);
    }

    @Test
    public void test2_validate() {
        logIn();
        Assert.assertTrue(sessionEndPoint.validate(session));
    }
}
