package pageObjects;

import base.DriverManagement;
import org.openqa.selenium.By;

public class MovieListPage extends BasePage{
    private By msgSuccess = By.id("successMsg");

    public String getMessageSuccess(){
        return DriverManagement.getText(msgSuccess);
    }
}
