import base.DriverManagement;
import base.MessageConstants;
import models.Movie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UpdateMovieTest extends BaseTest{
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

        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("dataTest");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        movieName = movie.getMovieName();

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();
    }
    @Test(description = "UPDATE MOVIE 22 - Xác minh dữ liệu của dropdown list Thể loại")
    public void VerifyDataOfCategoryList() throws Exception {
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
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("mandatory");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();
        System.out.println(movieName);

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();
    }

    @Test(description = "UPDATE MOVIE 45 - Sửa chi tiết phim thành công khi nhập dữ liệu hợp lệ vào tất cả các trường")
    public void UpdateMovieWithAllFields() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("all");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();
    }

    @Test(description = "UPDATE MOVIE 46 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Tên phim")
    public void UpdateMovieWithDeleteMovieName() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteMovieName");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorMovieNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 47 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Quốc gia")
    public void UpdateMovieWithDeleteNation() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteNation");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorNationMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 48 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Thời lượng")
    public void UpdateMovieWithDeleteTimeSlot() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteTimeSlot");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorTimeSlotMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 50 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Đạo diễn")
    public void UpdateMovieWithDeleteDirector() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteDirector");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorDirectorMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 51 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Nhà sản xuất")
    public void UpdateMovieWithDeleteProducer() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteProducer");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorProducerMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 52 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa trường Diễn viên")
    public void UpdateMovieWithDeleteActor() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteActor");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorActorMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 53 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi xóa tất cả các trường")
    public void UpdateMovieWithDeleteAllFields() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("deleteAll");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorMovieNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 54 - Xác minh rằng người dùng sửa chi tiết phim không thành công khi nhập dữ liệu không hợp lệ vào trường Thời lượng")
    public void UpdateMovieWithInvalidTimeSlotField() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("invalidTimeSlot");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickUpdateButton();

        String actualMessage = updateMoviePage.getErrorTimeSlotMessage();
        String expectedMessage = MessageConstants.INVALID_TIMESLOT;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "UPDATE MOVIE 55 - Xác minh rằng người dùng sửa chi tiết phim bị hủy thành công khi nhấn chọn nút Hủy")
    public void CancelUpdateMovie() throws Exception {
        setExcelFile("src/test/java/resource/Data.xlsx", "SuaPhim");
        List<Integer> movie1 = excelReader.getRowsByType("all");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);
        String movieName = movie.getMovieName();

        homePage.enterKeyWord(movieName);
        homePage.clickSearchButton();

        movieListPage.clickMovieDetailButton();
        moviePage.clickUpdateMovieButton();

        updateMoviePage.enterMovieInformation(movie);
        updateMoviePage.clickCancelButton();

        String actualMessage = homePage.getTitle();
        String expectedMessage = MessageConstants.TITLE_LIST_MOVIE;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @AfterMethod
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
}
