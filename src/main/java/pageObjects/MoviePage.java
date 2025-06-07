package pageObjects;

import base.DriverManagement;
import org.openqa.selenium.By;

public class MoviePage extends  BasePage{
    public void clickUpdateMovieButton(){
        By element = By.xpath(String.format(xpathLink, "Sửa"));
        DriverManagement.click(element);
    }

    public void clickDeleteMovieButton(){
        By element = By.xpath(String.format(xpathLink, "Xóa"));
        DriverManagement.click(element);
    }
}
