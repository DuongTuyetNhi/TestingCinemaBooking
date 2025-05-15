import base.DriverManagement;
import base.MessageConstants;
import models.User;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

import java.util.List;

import static base.DriverManagement.getDriver;

public class RegisterTest extends BaseTest{
    @Test(description = "REGISTER 03 - Xác minh rằng hệ thống quay lại trang Đăng nhập khi nhấn chọn liên kết Đăng nhập tại đây")
    public void NavigateToLoginPage(){
        DriverManagement.openCinemaPage();
        homePage.openRegisterPage();
        registerPage.goToLoginPage();

        String actualTitle = loginPage.getTitle();
        String expectedTitle = "Đăng nhập";
        Assert.assertEquals(actualTitle, expectedTitle, "Title is not correct!");
    }

    @Test(description = "REGISTER 05 - Xác minh rằng người dùng đăng ký tài khoản thành công khi nhập dữ liệu hợp lệ vào các trường bắt buộc")
    public void CreateUserWithMandatoryFields() throws Exception {
        DriverManagement.openCinemaPage();
        String CinemaWindow = getDriver().getWindowHandle();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("mandatory");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        String email = user.getEmail();
        String[] str = email.split("@");
        String mailName = str[0];
        String domainName = str[1];

        registerPage.submitRegisterForm(user);

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        mailPage.loginToEmail(mailName, domainName);
        String token = mailPage.getToken();

        DriverManagement.switchToWindow(CinemaWindow);
        tokenPage.submitTokenForm(token);

        loginPage.submitLoginForm(user.getEmail(), user.getPassword());

        String actualText = homePage.getTextUserEmail();
        String expectedText = "Xin chào " + user.getEmail();
        Assert.assertEquals(actualText, expectedText, "Message error is not match");
    }

    @Test(description = "REGISTER 06 - Xác minh rằng người dùng đăng ký tài khoản thành công khi nhập dữ liệu hợp lệ vào tất cả các trường")
    public void CreateUserWithAllFields() throws Exception {
        DriverManagement.openCinemaPage();
        String CinemaWindow = getDriver().getWindowHandle();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("all");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        String email = user.getEmail();
        String[] str = email.split("@");
        String mailName = str[0];
        String domainName = str[1];

        registerPage.submitRegisterForm(user);

        getDriver().switchTo().newWindow(WindowType.TAB);
        DriverManagement.openMailPage();
        mailPage.loginToEmail(mailName, domainName);
        String token = mailPage.getToken();

        DriverManagement.switchToWindow(CinemaWindow);
        tokenPage.submitTokenForm(token);

        loginPage.submitLoginForm(user.getEmail(), user.getPassword());

        String actualText = homePage.getTextUserEmail();
        String expectedText = "Xin chào " + user.getEmail();
        Assert.assertEquals(actualText, expectedText, "Message error is not match");
    }

    @Test(description = "REGISTER 08 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Tên")
    public void CreateUserWithBlankFirstNameField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankFirstName");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorFirstNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 09 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Họ")
    public void CreateUserWithBlankLastNameField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankLastName");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorLastNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 10 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Ngày sinh")
    public void CreateUserWithBlankBirthDayField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankBirthDay");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorBirthDayMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 11 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Số điện thoại")
    public void CreateUserWithBlankPhoneField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankPhone");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorPhoneMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 12 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Email")
    public void CreateUserWithBlankEmailField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankEmail");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorEmailMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 13 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Mật khẩu")
    public void CreateUserWithBlankPasswordField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankPassword");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorPasswordMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 14 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống trường Xác nhận mật khẩu")
    public void CreateUserWithBlankConfirmPasswordField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankConfirmPassword");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorConfirmPasswordMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 15 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi bỏ trống tất cả các trường")
    public void CreateUserWithBlankAllFields() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankAll");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorEmailMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 16 - Xác minh rằng người dùng kích hoạt tài khoản không thành công khi bỏ trống trường Xác thực tài khoản")
    public void CreateUserWithBlankTokenField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("blankToken");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);
        tokenPage.clickSendButton();

        String actualMessage = tokenPage.getErrorTokenMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 17 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi nhập dữ liệu không hợp lệ vào trường Số điện thoại")
    public void CreateUserWithInvalidPhoneField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("invalidPhone");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorPhoneMessage();
        String expectedMessage = MessageConstants.INVALID_PHONE;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }
    @Test(description = "REGISTER 18 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi nhập dữ liệu không hợp lệ vào trường Email")
    public void CreateUserWithInvalidEmailField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("invalidEmail");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorEmailMessage();
        String expectedMessage = MessageConstants.INVALID_EMAIL;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }
    @Test(description = "REGISTER 19 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi nhập dữ liệu không hợp lệ vào trường Mật khẩu")
    public void CreateUserWithInvalidPasswordField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("invalidPassword");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorPasswordMessage();
        String expectedMessage = MessageConstants.INVALID_PASSWORD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 20 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi nhập dữ liệu không hợp lệ vào trường Xác nhận mật khẩu")
    public void CreateUserWithInvalidConfirmPasswordField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("invalidConfirmPassword");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorInvalidConfirmPasswordMessage();
        String expectedMessage = MessageConstants.INVALID_CONFIRM_PASSWORD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 21 - Xác minh rằng người dùng đăng ký tài khoản không thành công khi nhập dữ liệu trùng lặp với Email đã đăng ký trước đó")
    public void CreateUserWithDuplicateEmailField() throws Exception {
        DriverManagement.openCinemaPage();

        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("duplicateEmail");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);

        String actualMessage = registerPage.getErrorDuplicateEmailMessage();
        String expectedMessage = MessageConstants.DUPLICATE_EMAIL;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "REGISTER 22 - Xác minh rằng người dùng kích hoạt tài khoản không thành công khi nhập dữ liệu không hợp lệ vào trường Mã xác thực")
    public void CreateUserWithInvalidTokenField() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openRegisterPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangKy");
        List<Integer> userRows = excelReader.getRowsByType("invalidToken");
        int rowNum = userRows.get(0);
        User user = excelReader.getUserFromExcel(rowNum);

        registerPage.submitRegisterForm(user);
        String invalidToken = "000000";
        tokenPage.submitTokenForm(invalidToken);

        String actualMessage = tokenPage.getErrorInvalidTokenMessage();
        String expectedMessage = MessageConstants.INVALID_TOKEN;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }
}
