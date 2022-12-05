import com.ffhs.carsharing_v2.helpers.LoginHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginHelperTest {

    @Test
    public void listTestDetails_positive() {

        Assertions.assertTrue(LoginHelper.validateUserLogin("admin", "admin"));
        Assertions.assertTrue(LoginHelper.validateUserLogin("aris", "aris"));
        Assertions.assertTrue(LoginHelper.validateUserLogin("andreas", "1234"));
    }

    @Test
    public void listTestDetails_negative() {
        Assertions.assertFalse(LoginHelper.validateUserLogin("alfred", "hitzkopf"));
        Assertions.assertFalse(LoginHelper.validateUserLogin("aris", "aris1"));
        Assertions.assertFalse(LoginHelper.validateUserLogin("andreas", "01234"));
    }

}
