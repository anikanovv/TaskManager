import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.SpringJPA;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringJPA.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1_create() {
        @Nullable final User user = userService.persist("Kool", "pass", "des", "12.12.2012", "12.12.2012", Role.USER);
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getName());
    }


    @Test
    public void test2_update() {
        userService.merge("login", "my", "wife", "is", "male", Role.USER, "18fabae5-7117-4b63-ac77-799c0b3908fd");
        @Nullable final User user = userService.findByName("login");
        Assert.assertNotNull(user);
        Assert.assertEquals("wife", user.getEmail());
    }

    @Test
    public void test3_updatePassword() {
        userService.updatePassword("user", "user", "user228");
        @Nullable final User user = userService.logIn("login", "user228");
        Assert.assertNotNull(user);
    }

    @Test
    public void test4_findOne() {
        @Nullable final User user = userService.findOne("18fabae5-7117-4b63-ac77-799c0b3908fd");
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getName());
    }

    @Test
    public void test5_findAll() throws Exception {
        @Nullable final List<User> list = userService.findAll();
        Assert.assertNotNull(list);
        Assert.assertEquals("user", list.get(0).getName());
    }

    @Test
    public void test6_remove() throws Exception {
        userService.remove("18fabae5-7117-4b63-ac77-799c0b3908fd");
        @Nullable final User user = userService.findByName("test");
        Assert.assertNull(user);
    }

    @Test
    public void test7_removeAll() throws Exception {
        userService.removeAll();
        @Nullable final List<User> list = userService.findAll();
        Assert.assertTrue(list.isEmpty());
    }
}

