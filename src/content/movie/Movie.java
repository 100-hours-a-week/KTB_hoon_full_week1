package content.movie;

import content.Content;
import enums.AgeRating;
import enums.Genre;

public class Movie extends Content {

    private final int releaseYear;
    private final String distributor;

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
