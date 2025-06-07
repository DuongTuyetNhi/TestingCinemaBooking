package pageObjects;


import base.DriverManagement;
import models.Movie;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class UpdateMoviePage extends BasePage {
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
    public void clickUpdateButton() {
        By btnUpdate = By.xpath(String.format(xpathButton, "LƯU"));
        DriverManagement.click(btnUpdate);
    }

    public void clickCancelButton(){
        By btnCancel = By.xpath(String.format(xpathButton, "HUỶ"));
        DriverManagement.click(btnCancel);
    }

    public void enterMovieInformation(Movie movie) {
        enterMovieName(movie.getMovieName());

        if (movie.getPhotoPath() != null && !movie.getPhotoPath().trim().isEmpty()) {
            inputPhoto(movie.getPhotoPath());
        }

        enterNation(movie.getNation());
        enterTimeSlot(movie.getTimeSlot());
        selectCategory(movie.getCategory());
        enterDirector(movie.getDirector());
        enterProducer(movie.getProducer());
        enterActor(movie.getActor());

        if (movie.getTrailer() != null && !movie.getTrailer().trim().isEmpty()) {
            enterTrailer(movie.getTrailer());
        }

        if (movie.getDescribeMovie() != null && !movie.getDescribeMovie().trim().isEmpty()) {
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
