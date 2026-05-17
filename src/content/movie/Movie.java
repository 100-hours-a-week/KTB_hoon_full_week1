package content.movie;

import content.Content;
import enums.AgeRating;
import enums.Genre;
import java.util.List;

public class Movie extends Content {

    private int releaseYear;
    private String distributor;

    protected Movie(
            String name,
            Genre genre,
            AgeRating ageRating,
            Integer runningTime,
            String description,
            int releaseYear,
            String distributor) {
        super(name, genre, ageRating, runningTime, description);
        this.releaseYear = releaseYear;
        this.distributor = distributor;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDistributor() {
        return distributor;
    }
}
