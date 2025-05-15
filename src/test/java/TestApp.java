import base.DriverManagement;
import org.testng.annotations.Test;

public class TestApp extends BaseTest{
    @Test(description = "User can log into Railway with valid account")
    public void LoginWithValidAccount() {
        DriverManagement.openCinemaPage();
    }
}
