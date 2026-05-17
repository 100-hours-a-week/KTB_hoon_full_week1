package enums.menu;

public enum MenuOption {
    VIEW_CONTENTS(1, "컨텐츠 전부 출력"),
    VIEW_CONTENT_INFO(2, "컨텐츠 조회"),
    ADD_CONTENTS(3, "컨텐츠 추가"),
    EXIT(0, "종료");

    private final int code;
    private final String label;

    MenuOption(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static MenuOption fromCode(int code) {
        for (MenuOption option : MenuOption.values()) {
            if (option.getCode() == code) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid MenuOption code: " + code);
    }
}
