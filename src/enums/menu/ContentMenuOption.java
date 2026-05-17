package enums.menu;

public enum ContentMenuOption {
    PLAY(0, "재생"),
    STOP(1, "정지");

    private final int code;
    private final String label;

    ContentMenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static ContentMenuOption fromCode(int code) {
        for (ContentMenuOption contentMenuOption : values()) {
            if (contentMenuOption.code == code) {
                return contentMenuOption;
            }
        }
        throw new IllegalArgumentException("Invalid Genre code: " + code);
    }
}
