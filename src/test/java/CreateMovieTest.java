import base.DriverManagement;
import base.MessageConstants;
import config.ConfigExcelReader;
import models.Movie;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CreateMovieTest extends BaseTest{
    @Test(description = "CREATE MOVIE 03 - Xác minh dữ liệu của dropdown list Thể loại")
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

        homePage.openAddMoviePage();

        setExcelFile("src/test/java/resource/Data.xlsx", "TheLoai");
        List<String> expectedOptions = excelReader.getCategoryListFromExcel();

        List<String> actualOptions = createMoviePage.getCategoryDropdownOptions();

        Assert.assertEquals(actualOptions, expectedOptions, "Dropdown options do not match expected values.");
    }

    @Test(description = "CREATE MOVIE 08 - Xác minh rằng người dùng thêm phim mới thành công khi nhập dữ liệu hợp lệ vào các trường bắt buộc")
    public void CreateMovieWithMandatoryFields() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("mandatory");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = movieListPage.getMessageSuccess();
        String expectedMessage = MessageConstants.MESSAGE_ADD_SUCCESS;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 09 - Xác minh rằng người dùng thêm phim mới thành công khi nhập dữ liệu hợp lệ vào tất cả các trường")
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
        List<Integer> movie1 = excelReader.getRowsByType("all");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = movieListPage.getMessageSuccess();
        String expectedMessage = MessageConstants.MESSAGE_ADD_SUCCESS;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 10 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Tên phim")
    public void CreateMovieWithBlankMovieNameField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankMovieName");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorMovieNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 11 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Quốc gia")
    public void CreateMovieWithBlankNationField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankNation");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorNationMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 12 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Thời lượng")
    public void CreateMovieWithBlankTimeSlotField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankTimeSlot");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorTimeSlotMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 14 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Đạo diễn")
    public void CreateMovieWithBlankDirectorField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankDirector");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorDirectorMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 15 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Nhà sản xuất")
    public void CreateMovieWithBlankProducerField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankProducer");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorProducerMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 16 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống trường Diễn viên")
    public void CreateMovieWithBlankActorField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankActor");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorActorMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 17 - Xác minh rằng người dùng thêm phim mới không thành công khi bỏ trống tất cả các trường")
    public void CreateMovieWithBlankAllFields() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("blankAll");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorMovieNameMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 18 - Xác minh rằng người dùng thêm phim mới không thành công khi nhập dữ liệu không hợp lệ vào trường Thời lượng")
    public void CreateMovieWithInvalidTimeSlotField() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("invalidTimeSlot");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickSaveButton();

        String actualMessage = createMoviePage.getErrorTimeSlotMessage();
        String expectedMessage = MessageConstants.MESSAGE_REQUIRED_FIELD;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }

    @Test(description = "CREATE MOVIE 19 - Xác minh rằng người dùng thêm phim mới bị hủy thành công khi nhấn chọn nút Hủy")
    public void CancelCreateMovie() throws Exception {
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
        List<Integer> movie1 = excelReader.getRowsByType("all");
        int rowMovieNum = movie1.get(0);
        Movie movie = excelReader.getMovieFromExcel(rowMovieNum);

        createMoviePage.enterMovieInformation(movie);
        createMoviePage.clickCancelButton();

        String actualMessage = homePage.getTitle();
        String expectedMessage = MessageConstants.TITLE_HOMEPAGE;
        Assert.assertEquals(actualMessage, expectedMessage, "Message error is not match");
    }
}
