package content.series;

import content.Content;
import enums.AgeRating;
import enums.Genre;
import enums.SeriesType;

public class Series extends Content {

    private int seasonNumber;
    private int episodeNumber;
    private SeriesType seriesType;

    public Series(
            String name,
            Genre genre,
            AgeRating ageRating,
            Integer runningTime,
            String description,
            int seasonNumber,
            int episodeNumber,
            SeriesType seriesType) {
        super(name, genre, ageRating, runningTime, description);
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.seriesType = seriesType;
    }

    @Override
    public void play() {
        System.out.println("📺 Starting series playback");
        System.out.println("─────────────────────────");
        printCommonInfo();
        System.out.println("Seasons: " + seasonNumber);
        System.out.println("Total Episodes: " + episodeNumber);
        System.out.println("Type: " + seriesType);
        if (seriesType == SeriesType.ORIGINAL) {
            System.out.println("[ N Original Series ]");
        }
        System.out.println("─────────────────────────");
        System.out.println("▶ Playing Season 1, Episode 1...");
    }

    @Override
    public void stop() {
        System.out.println("⏹ Stopped: " + getName());
        System.out.println("Thank you for watching!");
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public SeriesType getSeriesType() {
        return seriesType;
    }
}
