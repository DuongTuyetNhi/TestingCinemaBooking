package pageObjects;

import base.DriverManagement;
import models.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static base.DriverManagement.driver;

public class CreateMoviePage extends BasePage{
    private By ddlCategory = By.id("category.categoryId");
    private By txtMovieName = By.id("movieName");
    private By filPhoto = By.id("photo");
    private By txtNation = By.id("nation");
    private By txtTimeSlot = By.id("timeSlot");
    private By sltCategory  = By.id("category.categoryId");
    private By txtDirector = By.id("director");
    private By txtProducer = By.id("producer");
    private By txtActor = By.id("actor");
    private By txtTrailer = By.id("trailer");
    private By txtDescribeMovie = By.id("describeMovie");
    String xpathOptionCategory = "//select[@id='category.categoryId']/option[contains(text(), '%s')]";

    public List<String> getCategoryDropdownOptions() {
        WebElement dropdownElement = DriverManagement.getDriver().findElement(ddlCategory);
        Select select = new Select(dropdownElement);

        List<WebElement> options = select.getOptions();
        List<String> optionTexts = new ArrayList<>();
        for (WebElement option : options) {
            optionTexts.add(option.getText().trim());
        }
        return optionTexts;
    }
    public void enterMovieName(String movieName){
        DriverManagement.enter(txtMovieName, movieName);
    }
    public void inputPhoto(String moviePath){
        DriverManagement.enter(filPhoto, moviePath);
    }
    public void enterNation(String nation){
        DriverManagement.enter(txtNation, nation);
    }
    public void enterTimeSlot(String timeSlot){
        DriverManagement.enter(txtTimeSlot, timeSlot);
    }
    public void selectCategory(String category){
        DriverManagement.click(sltCategory);

        By optionCategory = By.xpath(String.format(xpathOptionCategory, category));
        DriverManagement.click(optionCategory);
    }
    public void enterDirector(String director){
        DriverManagement.enter(txtDirector, director);
    }
    public void enterProducer(String producer){
        DriverManagement.enter(txtProducer, producer);
    }
    public void enterActor(String actor){
        DriverManagement.enter(txtActor, actor);
    }
    public void enterTrailer(String trailer){
        DriverManagement.enter(txtTrailer, trailer);
    }
    public void enterDescription(String description){
        DriverManagement.enter(txtDescribeMovie, description);
    }
    public void clickSaveButton(){
        By btnSave = By.xpath(String.format(xpathButton, "LƯU"));
        DriverManagement.click(btnSave);
    }
    public void clickCancelButton(){
        By btnSave = By.xpath(String.format(xpathButton, "HUỶ"));
        DriverManagement.click(btnSave);
    }
    public void enterMovieInformation(Movie movie) {
        if (movie.getMovieName() != null && !movie.getMovieName().isEmpty()) {
            enterMovieName(movie.getMovieName());
        }
        if (movie.getPhotoPath() != null && !movie.getPhotoPath().isEmpty()) {
            inputPhoto(movie.getPhotoPath());
        }
        if (movie.getNation() != null && !movie.getNation().isEmpty()) {
            enterNation(movie.getNation());
        }
        if (movie.getTimeSlot() != null && !movie.getTimeSlot().isEmpty()) {
            enterTimeSlot(movie.getTimeSlot());
        }
        if (movie.getCategory() != null && !movie.getCategory().isEmpty()) {
            selectCategory(movie.getCategory());
        }
        if (movie.getDirector() != null && !movie.getDirector().isEmpty()) {
            enterDirector(movie.getDirector());
        }
        if (movie.getProducer() != null && !movie.getProducer().isEmpty()) {
            enterProducer(movie.getProducer());
        }
        if (movie.getActor() != null && !movie.getActor().isEmpty()) {
            enterActor(movie.getActor());
        }
        if (movie.getTrailer() != null && !movie.getTrailer().isEmpty()) {
            enterTrailer(movie.getTrailer());
        }
        if (movie.getDescribeMovie() != null && !movie.getDescribeMovie().isEmpty()) {
            enterDescription(movie.getDescribeMovie());
        }
    }
    public String getErrorMovieNameMessage(){
        return getErrorMessage("movieName");
    }
    public String getErrorNationMessage(){
        return getErrorMessage("nation");
    }
    public String getErrorTimeSlotMessage(){
        return getErrorMessage("timeSlot");
    }
    public String getErrorDirectorMessage(){
        return getErrorMessage("director");
    }
    public String getErrorProducerMessage(){
        return getErrorMessage("producer");
    }
    public String getErrorActorMessage(){
        return getErrorMessage("actor");
    }
}
