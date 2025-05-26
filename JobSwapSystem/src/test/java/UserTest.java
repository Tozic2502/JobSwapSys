import org.example.jobswapsystem.Models.User;
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
        userService = new UserService();
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
}
