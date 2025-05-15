package pageObjects;


import base.DriverManagement;
import org.openqa.selenium.By;

public class UpdateMoviePage extends CreateMoviePage {
    public void clickUpdateButton() {
        By btnUpdate = By.xpath(String.format(xpathButton, "LƯU"));
        DriverManagement.click(btnUpdate);
    }

    public void clickCancelButton(){
        By btnCancel = By.xpath(String.format(xpathButton, "HUỶ"));
        DriverManagement.click(btnCancel);
    }
}
