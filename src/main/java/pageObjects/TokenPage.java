package pageObjects;

import org.openqa.selenium.By;
import pageObjects.BasePage;

import static base.DriverManagement.*;

public class TokenPage extends BasePage {
    private By txtToken = By.id("token");
    private By btnSend = By.xpath("//form//button[@type='submit']");
    private By msgTokenError = By.xpath("//p[@class='message-token-error']");

    public void enterToken(String token){
        enter(txtToken, token);
    }
    public void clickSendButton(){
        click(btnSend);
    }
    public void submitTokenForm(String token){
        enterToken(token);
        clickSendButton();
    }
    public String getErrorTokenMessage(){
        return getErrorMessage("token");
    }
    public String getErrorInvalidTokenMessage(){
        return getText(msgTokenError);
    }
}
