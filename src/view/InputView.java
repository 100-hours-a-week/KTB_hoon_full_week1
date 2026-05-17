package view;

import dto.ContentAddReqDto;
import dto.LicensedMovieAddReqDto;
import dto.OriginalMovieAddReqDto;
import dto.SeriesAddReqDto;
import enums.AgeRating;
import enums.ContentTypeOption;
import enums.Genre;
import enums.SeriesType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readMenu() {
        System.out.print("선택 >> ");
        return readInt();
    }

    public static int selectContentId() {
        System.out.print("컨텐츠 ID >> ");
        return readInt();
    }

    public static ContentTypeOption readContentType() {
        System.out.println();
        System.out.println("─── 컨텐츠 종류 선택 ───");
        for (ContentTypeOption type : ContentTypeOption.values()) {
            System.out.printf("  [%d] %s%n", type.getCode(), type.getLabel());
        }
        System.out.println("  [0] 취소");
        int choice = readNumber("선택");
        if (choice == 0) {
            return null;
        }
        return ContentTypeOption.fromCode(choice);
    }

    public static ContentAddReqDto readContentInfo() {
        String name = readString("제목");
        Genre genre = readGenre();
        AgeRating ageRating = readAgeRating();
        Integer runningTime = readNumber("재생 시간 (분)");
        String description = readString("설명");
        return new ContentAddReqDto(name, genre, ageRating, runningTime, description);
    }

    public static OriginalMovieAddReqDto readOriginalMovieInfo(ContentAddReqDto contentInfo) {
        int releaseYear = readNumber("개봉 연도");
        String distributor = readString("배급사");
        LocalDate releaseDate = readDate("릴리즈 날짜/시간");
        return new OriginalMovieAddReqDto(contentInfo, releaseYear, distributor, releaseDate);
    }
    public static LicensedMovieAddReqDto readLicensedMovieInfo(ContentAddReqDto contentInfo) {
        int releaseYear = readNumber("개봉 연도");
        String distributor = readString("배급사");
        LocalDate startDate = readDate("라이센스 시작일");
        LocalDate endDate = readDate("라이센스 종료일");
        return new LicensedMovieAddReqDto(contentInfo, releaseYear, distributor, startDate, endDate);
    }

    public static SeriesAddReqDto readSeriesInfo(ContentAddReqDto contentInfo) {
        int seasonNumber = readNumber("시즌 수");
        int episodeNumber = readNumber("총 에피소드 수");
        SeriesType seriesType = readSeriesType();
        return new SeriesAddReqDto(contentInfo, seasonNumber, episodeNumber, seriesType);
    }

    public static SeriesType readSeriesType() {
        System.out.println();
        System.out.println("─── 시리즈 종류 선택 ───");
        SeriesType[] seriesTypes = SeriesType.values();
        for (SeriesType seriesType : seriesTypes) {
            System.out.printf("  [%d] %s%n", seriesType.getCode(), seriesType.getLabel());
        }
        int choice = readNumber("선택");
        if (choice < 1 || choice > seriesTypes.length) {
            throw new IllegalArgumentException("잘못된 장르입니다");
        }
        return SeriesType.fromCode(choice);
    }

    private static Genre readGenre() {
        System.out.println();
        System.out.println("─── 장르 선택 ───");
        Genre[] genres = Genre.values();
        for (Genre genre : genres) {
            System.out.printf("  [%d] %s%n", genre.getCode(), genre);
        }
        int choice = readNumber("선택");
        if (choice < 1 || choice > genres.length) {
            throw new IllegalArgumentException("잘못된 장르입니다");
        }
        return Genre.fromCode(choice);
    }

    private static AgeRating readAgeRating() {
        System.out.println();
        System.out.println("─── 시청 등급 선택 ───");
        AgeRating[] ratings = AgeRating.values();
        for (AgeRating rating : ratings) {
            System.out.printf("  [%d] %s%n", rating.getCode(), rating.getMinAge());
        }
        int choice = readNumber("선택");
        if (choice < 1 || choice > ratings.length) {
            throw new IllegalArgumentException("잘못된 등급입니다");
        }
        return AgeRating.fromCode(choice);
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            String input = readString(prompt + " (yyyy-MM-dd)");
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("날짜 형식이 잘못되었습니다. yyyy-MM-dd 형식으로 입력하세요");
            }
        }
    }

    private static int readNumber(String prompt) {
        System.out.print(prompt + " >> ");
        return readInt();
    }

    private static String readString(String prompt) {
        System.out.print(prompt + " >> ");
        return scanner.nextLine().trim();
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("0 이상 정수로 입력해주세요");
            }
        }
    }
}