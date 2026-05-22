package content.movie;

import enums.AgeRating;
import enums.Genre;
import java.time.LocalDate;

public class LicenseMovie extends Movie {

    private final LocalDate licenseStartDate;
    private final LocalDate licenseEndDate;

    public LicenseMovie(
            String name,
            Genre genre,
            AgeRating ageRating,
            int runningTime,
            String description,
            int releaseYear,
            String distributor,
            LocalDate licenseStartDate,
            LocalDate licenseEndDate) {
        super(name, genre, ageRating, runningTime, description, releaseYear, distributor);
        if(licenseStartDate == null || licenseEndDate == null){
            throw new IllegalArgumentException("License start date and end dates cannot be null");
        }
        if(licenseStartDate.isAfter(licenseEndDate)){
            throw new IllegalArgumentException("License start date cannot be after license end date");
        }
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
