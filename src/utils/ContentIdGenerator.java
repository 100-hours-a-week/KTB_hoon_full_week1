package utils;

public class ContentIdGenerator {

    private static int counter = 1;

    private ContentIdGenerator() {
    }

    public static int generateId() {
        return counter++;
    }
}
