import content.Content;
import dto.ContentAddReqDto;
import dto.LicensedMovieAddReqDto;
import dto.OriginalMovieAddReqDto;
import dto.SeriesAddReqDto;
import enums.ContentTypeOption;
import java.util.List;
import repository.ContentRepository;
import service.NetflixService;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        ContentRepository repository = new ContentRepository();
        NetflixService service = new NetflixService(repository);
        while(true){
            OutputView.printMainScreen();
            switch(InputView.readMenu()){
                case 0:
                    OutputView.printGoodbye();
                    return;
                case 1:
                    List<Content> contents = service.handleContentsView();
                    OutputView.printContentList(contents);
                    break;
                case 2:
                    int contentId = InputView.selectContentId();
                    Content content = service.getContent(contentId);
                    OutputView.printContent(content);
                    break;
                case 3:
                    ContentTypeOption option = InputView.readContentType();
                    if (option == null) {
                        OutputView.printMessage("취소되었습니다");
                        break;
                    }
                    else if (option == ContentTypeOption.ORIGINAL_MOVIE){
                        ContentAddReqDto contentAddRequest = InputView.readContentInfo();
                        OriginalMovieAddReqDto originalMovieAddRequest = InputView.readOriginalMovieInfo(contentAddRequest);
                        service.addOriginalMovie(originalMovieAddRequest);
                    } else if (option == ContentTypeOption.LICENSED_MOVIE) {
                        ContentAddReqDto contentAddRequest = InputView.readContentInfo();
                        LicensedMovieAddReqDto licensedMovieAddRequest = InputView.readLicensedMovieInfo(contentAddRequest);
                        service.addLicensedMovie(licensedMovieAddRequest);
                    } else if (option == ContentTypeOption.SERIES) {
                        ContentAddReqDto contentAddRequest = InputView.readContentInfo();
                        SeriesAddReqDto seriesAddRequest = InputView.readSeriesInfo(contentAddRequest);
                        service.addSeries(seriesAddRequest);
                    }
                    break;
                default:
                    OutputView.printInvalidMenuNumber();
                    break;
            }
        }
    }
}