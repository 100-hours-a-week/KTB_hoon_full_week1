import content.Content;
import dto.ContentAddReqDto;
import enums.ContentTypeOption;
import exception.ContentNotFoundException;
import java.util.List;
import repository.ContentRepository;
import service.NetflixService;
import view.InputView;
import view.OutputView;

public class Main {

    private static ContentRepository repository = new ContentRepository();
    private static NetflixService service = new NetflixService(repository);

    public static void main(String[] args) {
        while (true) {
            try {
                OutputView.printMainScreen();
                int menu = InputView.readMenu();
                if (menu == 0) {
                    OutputView.printGoodbye();
                    return;
                }
                handleMenu(menu);
            } catch (ContentNotFoundException e) {
                OutputView.printMessage("콘텐츠를 찾을 수 없습니다: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                OutputView.printMessage("잘못된 입력입니다: " + e.getMessage());
            } catch (Exception e) {
                OutputView.printMessage("알 수 없는 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }

    private static void handleMenu(int menu) {
        switch (menu) {
            case 1: handleContentList();   break;
            case 2: handleContentDetail(); break;
            case 3: handleAddContent();    break;
            default: OutputView.printInvalidMenuNumber();
        }
    }

    private static void handleContentList() {
        List<Content> contents = service.handleContentsView();
        OutputView.printContentList(contents);
    }

    private static void handleContentDetail() {
        int contentId = InputView.selectContentId();
        Content content = service.getContent(contentId);
        OutputView.printContent(content);
    }

    private static void handleAddContent() {
        ContentTypeOption option = InputView.readContentType();
        if (option == null) {
            OutputView.printMessage("취소되었습니다");
            return;
        }
        ContentAddReqDto contentAddRequest = InputView.readContentInfo();
        switch (option) {
            case ORIGINAL_MOVIE:
                service.addOriginalMovie(InputView.readOriginalMovieInfo(contentAddRequest));
                break;
            case LICENSED_MOVIE:
                service.addLicensedMovie(InputView.readLicensedMovieInfo(contentAddRequest));
                break;
            case SERIES:
                service.addSeries(InputView.readSeriesInfo(contentAddRequest));
                break;
        }
    }
}