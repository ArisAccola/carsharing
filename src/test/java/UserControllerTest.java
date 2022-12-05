import com.ffhs.carsharing_v2.controllers.UserController;
import com.ffhs.carsharing_v2.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserControllerTest {

    UserController user = new UserController();

    @Test
    public void listTestDetails_positive() {
        List<User> listUserDetails = user.getUser();

        Assertions.assertEquals(listUserDetails.size(), 8);
        Assertions.assertEquals(listUserDetails.get(0).getId(), 1);
        Assertions.assertEquals(listUserDetails.get(0).getUsername(), "admin");

    }

    @Test
    public void listTestDetails_negative() {
        List<User> listUserDetails = user.getUser();

        Assertions.assertNotEquals(listUserDetails.size(), 9);
        Assertions.assertNotEquals(listUserDetails.get(0).getId(), 2);
        Assertions.assertNotEquals(listUserDetails.get(0).getUsername(), "test");

    }

}
