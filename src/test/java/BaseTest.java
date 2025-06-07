import base.DriverManagement;
import config.ConfigExcelReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.*;

import static base.DriverManagement.getDriver;

public class BaseTest {
    protected ConfigExcelReader excelReader;

    @BeforeMethod
    @Parameters({"browser", "runMode"})
    public synchronized void beforeMethod(@Optional("chrome") String browser, @Optional("local") String runMode) throws Throwable {
        System.out.println("Pre-condition");
        DriverManagement.setBrowser(browser);
        DriverManagement.setRunMode(runMode);
        DriverManagement.initDriver();
        excelReader = new ConfigExcelReader();
    }

    public void setExcelFile(String filePath, String sheetName) throws Exception {
        excelReader.setExcelFile(filePath, sheetName);
    }

    @AfterMethod
    public synchronized void afterMethod() {
        System.out.println("Post-condition");
        getDriver().quit();
    }
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    MailPage mailPage = new MailPage();
    TokenPage tokenPage = new TokenPage();
    CreateMoviePage createMoviePage = new CreateMoviePage();
    MovieListPage movieListPage = new MovieListPage();
    UpdateMoviePage updateMoviePage = new UpdateMoviePage();
    MoviePage moviePage = new MoviePage();
    ConfigExcelReader excelHelper = new ConfigExcelReader();

}
