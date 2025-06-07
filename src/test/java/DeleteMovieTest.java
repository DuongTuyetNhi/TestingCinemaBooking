import base.DriverManagement;
import base.MessageConstants;
import models.Movie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
public class DeleteMovieTest extends BaseTest{
    private String movieName;
    @BeforeMethod
    public void CreateMovieWithAllFields() throws Exception {
        DriverManagement.openCinemaPage();
        homePage.openLoginPage();
        setExcelFile("src/test/java/resource/Data.xlsx", "DangNhap");
        List<Integer> adminAccountRows = excelReader.getRowsByType("admin");
        int rowNum = adminAccountRows.get(0);
        Map<String, String> account = excelReader.getLoginAccountFromExcel(rowNum);
        String email = account.get("email");
        String password = account.get("password");
        loginPage.submitLoginForm(email, password);

        homePage.openAddMoviePage();

        setExcelFile("src/test/java/resource/Data.xlsx", "ThemPhim");
        List<Integer> movie1 = excelReader.getRowsByType("dataTest");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        movieName = movie.getMovieName();

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();
    }

    @Test(description = "DELETE MOVIE 1 - Xác minh thông báo xác nhận xóa hiển thị khi người dùng nhấn chọn nút Xóa")
    public void DeleteMovie() throws Exception {
        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickDeleteMovieButton();

        String actualMessage = DriverManagement.getAlertText();
        String expectedMessage = "Bạn có chắc chắn muốn xóa phim '" + movieName + "'?";
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");

    }

    @Test(description = "DELETE MOVIE 2 - Xác minh người dùng xóa phim thành công khi nhấn chọn nút OK ở thông báo xác nhận xóa")
    public void DeleteMovieSucessfully() throws Exception {
        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickDeleteMovieButton();

        String actualMessage = DriverManagement.getAlertText();
        String expectedMessage = "Bạn có chắc chắn muốn xóa phim '" + movieName + "'?";
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");

        DriverManagement.acceptAlert();
    }

    @Test(description = "DELETE MOVIE 3 - Xác minh người dùng xóa phim không thành công khi nhấn chọn nút Cancel ở thông báo xác nhận xóa")
    public void DeleteMovieSuccesfully() throws Exception {
        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickDeleteMovieButton();

        String actualMessage = DriverManagement.getAlertText();
        String expectedMessage = "Bạn có chắc chắn muốn xóa phim '" + movieName + "'?";
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");

        DriverManagement.dismissAlert();
    }
}
