import base.DriverManagement;
import config.ConfigExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;

import java.util.List;
import java.util.Map;

public class LoginTest extends BaseTest{
    @Test(description = "LOGIN 02 - Xác minh rằng hệ thống quay lại trang Đăng ký khi nhấn chọn liên kết Đăng ký")
    public void NavigateToRegisterPage() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        loginPage.goToRegisterPage();

        String actualTitle = loginPage.getTitle();
        String expectedTitle = "Đăng ký";
        Assert.assertEquals(actualTitle, expectedTitle, "Title is not correct!");
    }

//    @Test(description = "LOGIN 03 - Xác minh rằng Admin đăng nhập thành công khi nhập đúng Email và Mật khẩu")
//    public void LoginToCinemaWithAdminAccount() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> adminAccountRows = excelReader.getRowsByType("admin");
//
//        int rowNum = adminAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualText = homePage.getTextUserEmail();
//        String expectedText = "Xin chào " + email;
//        Assert.assertEquals(actualText, expectedText, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 04 - Xác minh rằng User đăng nhập thành công khi nhập đúng Email và Mật khẩu")
//    public void LoginToCinemaWithUserAccount() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("user");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualText = homePage.getTextUserEmail();
//        String expectedText = "Xin chào " + email;
//        Assert.assertEquals(actualText, expectedText, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 05 - Xác minh rằng người dùng đăng nhập không thành công khi bỏ trống trường Email")
//    public void LoginToCinemaWithBlankEmail() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("blankEmail");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualMessage = loginPage.getErrorEmailBlankMessage();
//        String expectedMessage = "Vui lòng không bỏ trống trường này";
//        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 06 - Xác minh rằng người dùng đăng nhập không thành công khi bỏ trống trường Password")
//    public void LoginToCinemaWithBlankPassword() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("blankPassword");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualMessage = loginPage.getErrorPasswordBlankMessage();
//        String expectedMessage = "Vui lòng không bỏ trống trường này";
//        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 07 - Xác minh rằng người dùng đăng nhập không thành công khi bỏ trống tất cả các trường")
//    public void LoginToCinemaWithBlankAllField() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("blank");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//
//        String actualEmailMessage = loginPage.getErrorEmailBlankMessage();
//        String expectedEmailMessage = "Vui lòng không bỏ trống trường này";
//        Assert.assertEquals(actualEmailMessage, expectedEmailMessage, "Emai message error is not match");
//
//        String actualPasswordMessage = loginPage.getErrorPasswordBlankMessage();
//        String expectedPasswordMessage = "Vui lòng không bỏ trống trường này";
//        Assert.assertEquals(actualPasswordMessage, expectedPasswordMessage, "Password message error is not match");
//    }
//
//    @Test(description = "LOGIN 08 - Xác minh rằng người dùng đăng nhập không thành công khi nhập dữ liệu không hợp lệ vào trường Email")
//    public void LoginToCinemaWithInvalidEmail() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("invalidEmail");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualMessage = loginPage.getErrorMessage();
//        String expectedMessage = "Email hoặc mật khẩu không đúng. Vui lòng thử lại!";
//        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 09 - Xác minh rằng người dùng đăng nhập không thành công khi nhập dữ liệu không hợp lệ vào trường Password")
//    public void LoginToCinemaWithInvalidPassword() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("invalidPassword");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualMessage = loginPage.getErrorMessage();
//        String expectedMessage = "Email hoặc mật khẩu không đúng. Vui lòng thử lại!";
//        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
//    }
//
//    @Test(description = "LOGIN 10 - Xác minh rằng người dùng đăng nhập không thành công khi nhập dữ liệu không hợp lệ vào tất cả các trường")
//    public void LoginToCinemaWithInvalidAllField() throws Exception {
//        DriverManagement.openCinemaPage();
//        homePage.openLoginPage();
//        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
//        List<Integer> userAccountRows = excelReader.getRowsByType("invalid");
//
//        int rowNum = userAccountRows.get(0);
//        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
//
//        String email = account.get("email");
//        String password = account.get("password");
//
//        loginPage.submitLoginForm(email, password);
//
//        String actualMessage = loginPage.getErrorMessage();
//        String expectedMessage = "Email hoặc mật khẩu không đúng. Vui lòng thử lại!";
//        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
//    }
}
