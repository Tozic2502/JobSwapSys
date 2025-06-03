import org.example.jobswapsystem.Models.User;
import org.example.jobswapsystem.Repository.UserRepository;
import org.example.jobswapsystem.Service.IUserService;
import org.example.jobswapsystem.Service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest
{
    private static IUserService userService;

    /**
     * Runs before any test methods that may be to create an instance of our service
     */
    @BeforeAll
    static void init()
    {
        userService = new UserService(new UserRepository());
    }

    /**
     * Tests a user that is seeded into to database to see if login works
     */
    @Test
    void loginTest()
    {
        //arrange
        String email = "alice@example.com";
        String password = "pass123";

        //act
        User user = userService.login(email, password);

        //Assert
        assert user != null;
        assert user.getName().equals("Alice Johnson");
    }
    /*
    //Mikkel
    UnitTest example for IN, OUT, ON, OFF

    @Test
    void loginWithEmptyEmail_OUT() {
        User user = userService.login("", "pass123");
        assert user == null;
    }

    @Test
    void loginWithNullPassword_OUT() {
        User user = userService.login("alice@example.com", null);
        assert user == null;
    }

    @Test
    void loginWithMalformedEmail_OUT() {
        User user = userService.login("alice#example.com", "pass123"); // Invalid email format
        assert user == null;
    }

    @Test
    void loginWithWrongPassword_ON() {
        User user = userService.login("alice@example.com", "wrongpass");
        assert user == null;
    }

    @Test
    void loginWithTypoInEmail_OFF() {
        User user = userService.login("alcie@example.com", "pass123"); // typo: "alcie"
        assert user == null;
    }

     */

}
