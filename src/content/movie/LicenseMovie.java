package content.movie;

import enums.AgeRating;
import enums.Genre;
import java.time.LocalDate;

public class LicenseMovie extends Movie {

    private LocalDate licenseStartDate;
    private LocalDate licenseEndDate;

    public LicenseMovie(
            String name,
            Genre genre,
            AgeRating ageRating,
            Integer runningTime,
            String description,
            int releaseYear,
            String distributor,
            LocalDate licenseStartDate,
            LocalDate licenseEndDate) {
        super(name, genre, ageRating, runningTime, description, releaseYear, distributor);
        this.licenseStartDate = licenseStartDate;
        this.licenseEndDate = licenseEndDate;
    }

    public LocalDate getLicenseStartDate() {
        return licenseStartDate;
    }

    public LocalDate getLicenseEndDate() {
        return licenseEndDate;
    }
}
