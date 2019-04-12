import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.service.SessionService;
import ru.anikanov.tm.service.UserService;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class SessionTest {
    @Inject
    private SessionService sessionService;
    @Inject
    private UserService userService;

    private User getUser() {
        return userService.findByName("user");
    }

    @Test
    public void test1_create() {
        @Nullable final Session session = sessionService.persist("18fabae5-7117-4b63-ac77-799c0b3908fd");
        Assert.assertNotNull(session);
    }

    @Test
    public void test2_validate() {
        Session session = new Session(getUser().getId(), System.currentTimeMillis());
        Assert.assertTrue(sessionService.validate(session));
    }
}
