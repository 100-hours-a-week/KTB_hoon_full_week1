package view;

import content.Content;
import java.util.List;

public class OutputView {

    public static void printMainScreen() {
        printHeader();
        printMenu();
    }

    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void printContentList(List<Content> contents) {
        System.out.println();
        System.out.println("[ All Contents ]");
        printContentTable(contents);
    }

    public static void printContent(Content c) {
        printContentTableHeader();
        printContentRow(1, c);
        printContentTableFooter();
        System.out.println();
    }

    public static void printGoodbye() {
        System.out.println("안녕히가세요!");
    }

    public static void printInvalidMenuNumber(){
        System.out.println("올바르지 않는 번호입니다.");
    }

    private static void printHeader() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("                 🎬  N E T F L I X  🎬              ");
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }

    // 헤더 출력
    private static void printContentTableHeader() {
        System.out.println("┌─────┬─────┬────────────────────────┬────────────┐");
        System.out.printf("│ %-3s │ %-3s │ %-22s │ %-10s │%n", "No.", "ID", "Title", "Genre");
        System.out.println("├─────┼─────┼────────────────────────┼────────────┤");
    }

    // 푸터 출력
    private static void printContentTableFooter() {
        System.out.println("└─────┴─────┴────────────────────────┴────────────┘");
    }

    // 개별 row 출력 (No. 포함)
    private static void printContentRow(int no, Content content) {
        System.out.printf("│ %-3d │ %-3d │ %-22s │ %-10s │ %-6s │%n",
                no,
                content.getId(),
                truncate(content.getName(), 22),
                content.getGenre(),
                content.getAgeRating().getMinAge());
    }

    // 다중 출력
    private static void printContentTable(List<Content> contents) {
        printContentTableHeader();
        if (contents.isEmpty()) {
            System.out.println("│            (No contents registered)                      │");
        } else {
            for (int i = 0; i < contents.size(); i++) {
                printContentRow(i + 1, contents.get(i));
            }
        }
        printContentTableFooter();
        System.out.println("Total: " + contents.size() + " contents");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("─────────────── MENU ───────────────");
        System.out.println("  [1] 컨텐츠 출력");
        System.out.println("  [2] 컨텐츠 조회");
        System.out.println("  [3] 컨텐츠 추가");
        System.out.println("  [0] 종료");
        System.out.println("─────────────────────────────────────");
    }

    private static String truncate(String s, int maxLength) {
        if (s.length() <= maxLength) {
            return s;
        }
        return s.substring(0, maxLength - 3) + "...";
    }
}