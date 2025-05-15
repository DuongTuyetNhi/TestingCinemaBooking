package pageObjects;

import base.DriverManagement;
import enums.Hyperlink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static base.DriverManagement.*;

public class BasePage {
    protected String menuHyperlink = "//a[contains(text(),'%s')]";
    protected By txtUserEmail = By.xpath("//p[@class='hello-user']");
    private By txtSearchInput = By.name("searchInput");
    private By lnkDetailButton = By.xpath("//a[contains(text(),'Chi tiết')]");
    String xpathButton = "//button[contains(text(), '%s')]";
    String xpathLink = "//a[contains(text(), '%s')]";

    public String getTitle() {
        return driver.get().getTitle();
    }
    public boolean checkLoginHyperlinkIsDisplayed(){
        return getMenuHyperlink(Hyperlink.DangNhap).isDisplayed();
    }
    public boolean checkLogoutHyperlinkIsDisplayed(){
        return getMenuHyperlink(Hyperlink.DangXuat).isDisplayed();
    }

    public String getTextUserEmail(){
        return getText(txtUserEmail);
    }

    protected WebElement getMenuHyperlink(Hyperlink hyperlink){
        By byHyperlink = By.xpath(String.format(menuHyperlink, hyperlink.getValueHyperlink()));
        return getDriver().findElement(byHyperlink);
    }
    public void openRegisterPage(){
        this.getMenuHyperlink(Hyperlink.DangKy).click();
    }
    public void openLoginPage(){
        this.getMenuHyperlink(Hyperlink.DangNhap).click();
    }
    public void openAddMoviePage(){
        this.getMenuHyperlink(Hyperlink.ThemPhim).click();
    }
    public void clickLogoutHyperlink(){
        this.getMenuHyperlink(Hyperlink.DangXuat).click();
    }
    public String getErrorMessage(String fieldName){
        return DriverManagement.getValidationMessage(fieldName);
    }
    public void enterKeyWord(String keyWord){
        DriverManagement.enter(txtSearchInput, keyWord);
    }
    public void clickMovieDetailButton(){
        DriverManagement.waitForElementVisible(lnkDetailButton, 25);
        DriverManagement.clickFirstElement(lnkDetailButton);
    }
}
