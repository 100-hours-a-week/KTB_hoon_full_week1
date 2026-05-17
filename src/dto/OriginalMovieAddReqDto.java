package dto;

import java.time.LocalDate;

public record OriginalMovieAddReqDto(
        ContentAddReqDto contentInfo,
        int releaseYear,
        String distributor,
        LocalDate releaseDate
) {
}