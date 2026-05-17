package enums;

public enum SeriesType {
    ORIGINAL(1, "오리지널"),
    LICENSE(2, "라이센스");

    private final int code;
    private final String label;

    SeriesType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static SeriesType fromCode(int code) {
        for (SeriesType seriesType : values()) {
            if (seriesType.code == code) {
                return seriesType;
            }
        }
        throw new IllegalArgumentException("Invalid SeriesType code: " + code);
    }
}