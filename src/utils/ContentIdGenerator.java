package utils;

public class ContentIdGenerator {

    private static int counter = 1; // 동시성 고려 X

    private ContentIdGenerator() {
    }

    public static int generateId() {
        return counter++;
    }
}
