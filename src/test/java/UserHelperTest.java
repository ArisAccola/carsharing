import com.ffhs.carsharing_v2.helpers.UserHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserHelperTest {

    UserHelper user = new UserHelper();

    @Test
    public void listTestDetails_positive() {
        Assertions.assertTrue(UserHelper.createUser("test1", "test1"));
    }
}

