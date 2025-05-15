import base.DriverManagement;
import base.MessageConstants;
import models.Movie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UpdateMovieTest extends BaseTest{
    @Test(description = "UPDATE MOVIE 22 - Xác minh dữ liệu của dropdown list Thể loại")
    public void VerifyDataOfCategoryList() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
        List<Integer> adminAccountRows = excelReader.getRowsByType("admin");
        int rowNum = adminAccountRows.get(0);
        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
        String email = account.get("email");
        String password = account.get("password");
        loginPage.submitLoginForm(email, password);

        setExcelFile("src/test/java/resource/Data.xlsx", "ThemPhim");
        List<Integer> movie1 = excelReader.getRowsByType("all");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        setExcelFile("src/test/java/resource/Data.xlsx", "TheLoai");
        List<String> expectedOptions = excelReader.getCategoryListFromExcel();
        List<String> actualOptions = createMoviePage.getCategoryDropdownOptions();
        Assert.assertEquals(actualOptions, expectedOptions, "Dropdown options do not match expected values.");
    }

    @Test(description = "UPDATE MOVIE 23 - Xác minh rằng người dùng sửa chi tiết phim thành công khi nhập dữ liệu hợp lệ vào các trường bắt buộc")
    public void UpdateMovieWithMandatoryFields() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
        List<Integer> adminAccountRows = excelReader.getRowsByType("admin");
        int rowNum = adminAccountRows.get(0);
        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
        String email = account.get("email");
        String password = account.get("password");
        loginPage.submitLoginForm(email, password);

        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("mandatory");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = movieListPage.getMessageSuccess();
        String expectedMessage = MessageConstants.MESSAGE_ADD_SUCCESS;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }
}
