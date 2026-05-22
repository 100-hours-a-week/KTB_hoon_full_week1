package content.movie;

import enums.AgeRating;
import enums.Genre;
import java.time.LocalDate;
public class OriginalMovie extends Movie {

    private final LocalDate releaseDate;

    public OriginalMovie(
            String name,
            Genre genre,
            AgeRating ageRating,
            int runningTime,
            String description,
            int releaseYear,
            String distributor,
            LocalDate releaseDate) {
        super(name, genre, ageRating, runningTime, description, releaseYear, distributor);
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
