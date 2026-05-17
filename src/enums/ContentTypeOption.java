package enums;

public enum ContentTypeOption {
    ORIGINAL_MOVIE(1, "오리지널 영화"),
    LICENSED_MOVIE(2, "라이센스 영화"),
    SERIES(3, "시리즈");

    private final int code;
    private final String label;

    ContentTypeOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }
    public String getLabel() {
        return label;
    }

    public static ContentTypeOption fromCode(int code) {
        for (ContentTypeOption contentTypeOption : values()) {
            if (contentTypeOption.code == code){
                return contentTypeOption;
            }
        }
        throw new IllegalArgumentException("Invalid ContentTypeOption code: " + code);
    }
}
