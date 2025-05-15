package pageObjects;

import base.DriverManagement;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static base.DriverManagement.*;

public class MailPage extends BasePage{
    private By cbxScrambleAddress = By.id("use-alias");
    private By txtEmail = By.id("email-widget");
    private By txtEmailConfirm = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Please confirm your account')]//span");
    private By txtLinkConfirm = By.xpath("//*[@id='display_email']//a[contains(@href,'Confirm')]");
    private By btnMail = By.xpath("//*[@id='inbox-id']");
    private By txtMailName = By.xpath("//*[@id='inbox-id']/input");
    private By btnSet = By.xpath("//*[@id='inbox-id']/button[@class='save button small']");
    private By sltDomainName = By.xpath("//select[@id='gm-host-select']");
    private By txtEmailToken = By.xpath("//table[@id='email_table']//tr[contains(@class, 'mail')]//td[contains(.,'Mã xác thực của bạn')]//span");
    private By txtEmailContent = By.xpath("//*[@class='email_body']/pre");

    public String getEmail(){
        getDriver().findElement(cbxScrambleAddress).click();
        String email = getDriver().findElement(txtEmail).getText();
        return email;
    }

    public void confirmAccount(){
        DriverManagement.waitForElementVisible(txtEmailConfirm, 10);
        click(txtEmailConfirm);

        DriverManagement.waitForElementVisible(txtLinkConfirm, 10);
        click(txtLinkConfirm);
    }

    public void loginToEmail(String mailName, String domainName){
        click(btnMail);
        enter(txtMailName, mailName);
        click(btnSet);
        selectByValue(sltDomainName, domainName);
    }
    public String getToken(){
        click(txtEmailToken);

        String messageText = getText(txtEmailContent);
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(messageText);

        String token = "";
        if (matcher.find()) {
            token = matcher.group();
        }
        return token;
    }
}
