package pageObjects;

import base.DriverManagement;
import org.openqa.selenium.By;

public class LoginPage extends BasePage{
    private By lnkRegister = By.xpath("//a[@href='/register']");
    private By txtEmail = By.id("email");
    private By txtPassword = By.id("password");
    private By btnLogin = By.xpath("//form//input[@type='submit']");
    private By msgError = By.xpath("//p[@class='error-message']");
    public void submitLoginForm(String email, String password){
        DriverManagement.enter(txtEmail, email);
        DriverManagement.enter(txtPassword, password);
        DriverManagement.click(btnLogin);
    }
    public void goToRegisterPage(){
        DriverManagement.click(lnkRegister);
    }
    public String getErrorEmailBlankMessage(){
        return DriverManagement.getValidationMessage("email");
    }
    public String getErrorPasswordBlankMessage(){
        return DriverManagement.getValidationMessage("password");
    }
    public String getErrorMessage(){
        return DriverManagement.getText(msgError);
    }
}
