package content.movie;

import content.Content;
import enums.AgeRating;
import enums.Genre;
import java.util.List;

public class Movie extends Content {

    private int releaseYear;
    private String distributor;

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

    @Override
    public void play() {
        System.out.println("🎬 영화 재생을 시작합니다");
        System.out.println("─────────────────────────");
        printCommonInfo();
        System.out.println("개봉연도: " + releaseYear);
        System.out.println("배급사: " + distributor);
        System.out.println("─────────────────────────");
        System.out.println("▶ 재생 중...");
    }

    @Override
    public void stop() {
        System.out.println("⏹ " + name + " (" + releaseYear + ") 재생 종료");
        System.out.println("배급사: " + distributor + " | 감상해 주셔서 감사합니다");
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDistributor() {
        return distributor;
    }
}
