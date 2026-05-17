package exception;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(int id) {
        super("존재하지 않는 컨텐츠입니다 : id = " + id);
    }
}
