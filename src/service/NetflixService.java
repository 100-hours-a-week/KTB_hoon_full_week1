package service;

import content.Content;
import content.movie.LicenseMovie;
import content.movie.OriginalMovie;
import content.series.Series;
import dto.ContentAddReqDto;
import dto.LicensedMovieAddReqDto;
import dto.OriginalMovieAddReqDto;
import dto.SeriesAddReqDto;
import exception.ContentNotFoundException;
import java.util.List;
import repository.ContentRepository;

public class NetflixService {

    private final ContentRepository contentRepository;

    public NetflixService(ContentRepository repository) {
        this.contentRepository = repository;
    }

    public List<Content> handleContentsView(){
        return contentRepository.getAll();
    }

    public Content handlePlayContent(int contentId){
        Content content = getContentOrThrow(contentId);
        content.play();
        return content;
    }

    public void addOriginalMovie(OriginalMovieAddReqDto dto) {
        ContentAddReqDto info = dto.contentInfo();
        OriginalMovie movie = new OriginalMovie(
                info.name(),
                info.genre(),
                info.ageRating(),
                info.runningTime(),
                info.description(),
                dto.releaseYear(),
                dto.distributor(),
                dto.releaseDate()
        );
        contentRepository.add(movie);
    }

    public void addLicensedMovie(LicensedMovieAddReqDto dto) {
        ContentAddReqDto info = dto.contentInfo();
        LicenseMovie movie = new LicenseMovie(
                info.name(),
                info.genre(),
                info.ageRating(),
                info.runningTime(),
                info.description(),
                dto.releaseYear(),
                dto.distributor(),
                dto.licenseStartDate(),
                dto.licenseEndDate()
        );
        contentRepository.add(movie);
    }

    public void addSeries(SeriesAddReqDto dto) {
        ContentAddReqDto info = dto.contentInfo();
        Series series = new Series(
                info.name(),
                info.genre(),
                info.ageRating(),
                info.runningTime(),
                info.description(),
                dto.seasonNumber(),
                dto.episodeNumber(),
                dto.seriesType()
        );
        contentRepository.add(series);
    }

    private Content getContentOrThrow(int contentId) {
        return contentRepository.get(contentId)
                .orElseThrow(() -> new ContentNotFoundException(contentId));
    }
}
