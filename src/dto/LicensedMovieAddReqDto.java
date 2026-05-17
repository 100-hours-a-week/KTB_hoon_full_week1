package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LicensedMovieAddReqDto(
        ContentAddReqDto contentInfo,
        int releaseYear,
        String distributor,
        LocalDate licenseStartDate,
        LocalDate licenseEndDate
) {
}