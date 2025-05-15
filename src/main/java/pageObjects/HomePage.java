package pageObjects;

import base.DriverManagement;
import org.openqa.selenium.By;

public class HomePage extends BasePage{
    public void clickSearchButton(){
        By element = By.xpath(String.format(xpathButton, "Tìm kiếm"));
        DriverManagement.click(element);
    }
    public void goToBookTicketPage(){
        By element = By.xpath(String.format(xpathButton, "Đặt vé"));
        DriverManagement.click(element);
    }
}
