package pageObjects;

import base.DriverManagement;
import models.User;
import org.openqa.selenium.By;

import static base.DriverManagement.*;

public class RegisterPage extends BasePage{
    private By lnkLogin = By.xpath("//a[@href='/login']");
    private By txtFirstName = By.id("firstName");
    private By txtLastName = By.id("lastName");
    private By dtpDOB = By.id("birthDay");
    private By txtPhone = By.id("phone");
    private By txtEmail = By.id("email");
    private By txtAddress = By.id("address");
    private By txtPassword = By.id("password");
    private By txtConfirmPassword = By.id("confirmPassword");
    private By btnRegister = By.xpath("//form//input[@type='submit']");
    private By msgDuplicateEmailError = By.id("registrationError");
    private By msgConfirmPasswordError = By.xpath("//input[@id='confirmPassword']/following-sibling::span[@class='error']");

    public void goToLoginPage(){
        click(lnkLogin);
    }
    public void enterFirstName(String firstName){
        enter(txtFirstName, firstName);
    }
    public void enterLastName(String lastName){
        enter(txtLastName, lastName);
    }
    public void enterDateOfBirth(String dateOfBirth){
        enter(dtpDOB, dateOfBirth);
    }
    public void enterPhone(String phone){
        enter(txtPhone, phone);
    }
    public void enterEmail(String email){
        enter(txtEmail, email);
    }
    public void enterAddress(String address){
        enter(txtAddress, address);
    }
    public void enterPassword(String password){
        enter(txtPassword, password);
    }
    public void enterConfirmPassword(String confirmPassword){
        enter(txtConfirmPassword, confirmPassword);
    }
    public void clickRegisterButton(){
        click(btnRegister);
    }


    public void submitRegisterForm(User user) {
        if (user.getFirstName() != null) enterFirstName(user.getFirstName());
        if (user.getLastName() != null) enterLastName(user.getLastName());
        if (user.getDOB()  != null) enterDateOfBirth(user.getDOB());
        if (user.getPhone() != null) enterPhone(user.getPhone());
        if (user.getEmail() != null) enterEmail(user.getEmail());
        if (user.getAddress() != null) enterAddress(user.getAddress());
        if (user.getPassword() != null) enterPassword(user.getPassword());
        if (user.getConfirmPassword() != null) enterConfirmPassword(user.getConfirmPassword());
        clickRegisterButton();
    }
    public String getErrorFirstNameMessage(){
        return getErrorMessage("firstName");
    }
    public String getErrorLastNameMessage(){
        return getErrorMessage("lastName");
    }
    public String getErrorBirthDayMessage(){
        return getErrorMessage("birthDay");
    }
    public String getErrorPhoneMessage(){
        return getErrorMessage("phone");
    }
    public String getErrorEmailMessage(){
        return getErrorMessage("email");
    }
    public String getErrorPasswordMessage(){
        return getErrorMessage("password");
    }
    public String getErrorConfirmPasswordMessage(){
        return getErrorMessage("confirmPassword");
    }
    public String getErrorInvalidConfirmPasswordMessage(){
        return getText(msgConfirmPasswordError);
    }
    public String getErrorDuplicateEmailMessage(){
        return getText(msgDuplicateEmailError);
    }
}
