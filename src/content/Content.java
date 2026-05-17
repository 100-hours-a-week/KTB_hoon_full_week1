package content;

import enums.AgeRating;
import enums.Genre;
import utils.ContentIdGenerator;

public abstract class Content {

    protected final int id;
    protected String name;
    protected Genre genre;
    protected AgeRating ageRating;
    protected Integer runningTime;
    protected String description;

    protected Content(String name, Genre genre, AgeRating ageRating, Integer runningTime,
            String description) {
        this.id = ContentIdGenerator.generateId();
        this.name = name;
        this.genre = genre;
        this.ageRating = ageRating;
        this.runningTime = runningTime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    protected void printCommonInfo() {
        System.out.println("제목: " + name);
        System.out.println("장르: " + genre.getLabel());
        System.out.println("등급: " + ageRating.getLabel());
        System.out.println("러닝타임: " + runningTime + "분");
        System.out.println("줄거리: " + description);
    }
}
