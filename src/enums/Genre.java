package enums;

public enum Genre {
    ACTION(1, "액션"),
    COMEDY(2, "코미디"),
    ROMANCE(3, "로맨스"),
    THRILLER(4, "스릴러"),
    HORROR(5, "공포"),
    SF(6, "SF"),
    FANTASY(7, "판타지"),
    DRAMA(8, "드라마"),
    ANIMATION(9, "애니메이션"),
    DOCUMENTARY(10, "다큐멘터리");

    private final int code;
    private final String label;

    Genre(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static Genre fromCode(int code) {
        for (Genre genre : values()) {
            if (genre.code == code) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Invalid Genre code: " + code);
    }
}