package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static config.ConfigReader.getProperty;


public class DriverManagement {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    private static ThreadLocal<String> runMode = new ThreadLocal<>();

    public static synchronized void initDriver() throws Exception {
        String browser = getBrowser().toLowerCase();
        String runMode = getRunMode().toLowerCase();

        switch (runMode) {
            case "local":
                switch (browser) {
                    case "chrome":
                        driver.set(new ChromeDriver());
                        break;
                    case "firefox":
                        driver.set(new FirefoxDriver());
                        break;
                    default:
                        driver.set(new ChromeDriver());
                        break;
                }
                break;
            case "grid":
                String remoteUrl = getProperty("remoteUrl");
                switch (browser) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), chromeOptions));
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), firefoxOptions));
                        break;
                    default:
                        ChromeOptions defaultOptions = new ChromeOptions();
                        driver.set(new RemoteWebDriver(new URL(remoteUrl), defaultOptions));
                        break;
                }
                break;
        }
        DriverManagement.getDriver().manage().window().maximize();
    }

    public static void setBrowser(String browserName) {
        browser.set(browserName);
    }

    public static String getBrowser() {
        return browser.get();
    }

    public static void setRunMode(String runModeName) {
        runMode.set(runModeName);
    }

    public static String getRunMode() {
        return runMode.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    public static void openCinemaPage(){
        int retryCount = 5;
        int retry = 0;
        while (retry < retryCount) {
            try {
                getDriver().get(config.ConfigReader.getProperty("cinema_url"));
                if (DriverManagement.getDriver().getTitle().contains("DoubleT")) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Retry " + (retry+1) + " failed.");
            }
            retry++;
        }
        if (retry == retryCount) {
            throw new RuntimeException("Failed to load cinema page after " + retryCount + " attempts.");
        }
    }


    public static void openMailPage(){
        getDriver().get(getProperty("email_url"));
    }

    public static void enter(By element, String information){
        getDriver().findElement(element).clear();
        getDriver().findElement(element).sendKeys(information);
    }
    public static void selectByValue(By element, String value) {
        WebElement dropdownElement = DriverManagement.getDriver().findElement(element);
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    public static void click(By element){
        waitForElementVisible(element, 20);
        getDriver().findElement(element).click();
    }
    public static void clickFirstElement(By locator) {
        List<WebElement> elements = findElements(locator);
        if (!elements.isEmpty()) {
            elements.get(0).click();
        } else {
            System.out.println("No element found with the provided locator.");
        }
    }
    public static List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }
    public static String getValidationMessage(String field) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManagement.getDriver();
        return (String) js.executeScript("return document.getElementById('" + field + "').validationMessage;");
    }

    public static List<String> getDropdownOptions(By element) {
        WebElement dropdownElement = getDriver().findElement(element);
        Select select = new Select(dropdownElement);

        List<WebElement> options = select.getOptions();
        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }
    public static void selectOptionByText(By locator, String text) {
        WebElement dropdown = getDriver().findElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }
    public static String getText(By element){
        waitForElementVisible(element, 10);
        return getDriver().findElement(element).getText();
    }
    public static boolean isDisplayed(By element){
        return getDriver().findElement(element).isDisplayed();
    }
    public static void waitForElementVisible(By xpath, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public static void scrollToView(By element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement Element = getDriver().findElement(element);
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }
    public static void switchToWindow(String windowHandle) {
        DriverManagement.getDriver().switchTo().window(windowHandle);
        System.out.println("Switched to window: " + windowHandle);
    }

}
