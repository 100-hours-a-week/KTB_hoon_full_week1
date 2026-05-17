package enums;

public enum AgeRating {
    ALL(1, 0, "전체관람가"),
    AGE_12(2, 12, "12세이용가"),
    AGE_15(3, 15, "15세이용가"),
    AGE_19(4, 19, "청소년관람불가");

    private final int code;
    private final int minAge;
    private final String label;

    AgeRating(int code, int minAge, String label) {
        this.code = code;
        this.minAge = minAge;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getLabel() {
        return label;
    }

    public static AgeRating fromCode(int code) {
        for (AgeRating ageRating : values()) {
            if (ageRating.code == code){
                return ageRating;
            }
        }
        throw new IllegalArgumentException("Invalid AgeRating code: " + code);
    }
}