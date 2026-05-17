package dto;

import enums.SeriesType;

public record SeriesAddReqDto(
        ContentAddReqDto contentInfo,
        int seasonNumber,
        int episodeNumber,
        SeriesType seriesType
) {
}