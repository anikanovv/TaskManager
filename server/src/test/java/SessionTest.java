import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.service.SessionService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.SpringJPA;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJPA.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionTest {
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    private User getUser() {
        return userService.findByName("volodya");
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
