package models;

public class Movie {
    String movieName;
    String photoPath;
    String nation;
    String timeSlot;
    String category;
    String director;
    String producer;
    String actor;
    String trailer;
    String describeMovie;

    public Movie() {
    }

    public Movie(String movieName, String photoPath, String nation, String timeSlot, String category, String director, String producer, String actor, String trailer, String describeMovie) {
        this.movieName = movieName;
        this.photoPath = photoPath;
        this.nation = nation;
        this.timeSlot = timeSlot;
        this.category = category;
        this.director = director;
        this.producer = producer;
        this.actor = actor;
        this.trailer = trailer;
        this.describeMovie = describeMovie;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDescribeMovie() {
        return describeMovie;
    }

    public void setDescribeMovie(String describeMovie) {
        this.describeMovie = describeMovie;
    }
}
