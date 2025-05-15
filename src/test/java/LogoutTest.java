import base.DriverManagement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LogoutTest extends BaseTest{
    @Test(description = "LOGOUT 11 - Xác minh rằng Admin đăng xuất thành công khi nhấn chọn liên kết Đăng xuất")
    public void LogoutByAdminAccount() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
        List<Integer> adminAccountRows = excelReader.getRowsByType("admin");

        int rowNum = adminAccountRows.get(0);
        String email = excelReader.getCellData("email", rowNum);
        String password = excelReader.getCellData("password", rowNum);
        loginPage.submitLoginForm(email, password);

        homePage.clickLogoutHyperlink();

        Assert.assertTrue(homePage.checkLoginHyperlinkIsDisplayed(), "Logout unsuccessfully");
    }

    @Test(description = "LOGOUT 12 - Xác minh rằng User đăng xuất thành công khi nhấn chọn liên kết Đăng xuất")
    public void LoginToCinemaWithUserAccount() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
        List<Integer> userAccountRows = excelReader.getRowsByType("user");

        int rowNum = userAccountRows.get(0);
        String email = excelReader.getCellData("email", rowNum);
        String password = excelReader.getCellData("password", rowNum);
        loginPage.submitLoginForm(email, password);

        homePage.clickLogoutHyperlink();

        Assert.assertTrue(homePage.checkLoginHyperlinkIsDisplayed(), "Logout unsuccessfully");
    }
}
