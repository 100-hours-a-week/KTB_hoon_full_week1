package dto;

import enums.AgeRating;
import enums.Genre;

public record ContentAddReqDto (
         String name,
         Genre genre,
         AgeRating ageRating,
         Integer runningTime,
         String description
) {
}
