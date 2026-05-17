# 회고
넷플릭스 도메인을 모티브로 한 콘텐츠 관리 콘솔 애플리케이션을 구현했습니다. Content를 상위 개념으로 두고 Movie, Series 등 구체적인 콘텐츠 타입으로 상속 구조를 설계했으며, 콘텐츠 등록·조회·출력 기능을 콘솔에서 제공합니다.
주요 사용 기술 및 개념은 다음과 같다.
- 객체지향 설계 (상속, 추상화)
- 도메인/View/Repository 계층 분리
- enum을 활용한 정적 데이터 표현 (Genre, AgeRating 등)


## 고민들 + 질문
1. Content 조부모 클래스를 일반 클래스로 정의할 것인가, 추상클래스로 정의할것인가

   우선 저는 추상클래스로 Content를 정의했습니다.

    ```java
    public abstract class Content {
    
        protected final int id;
        protected String name;
        protected Genre genre;
        protected AgeRating ageRating;
        protected Integer runningTime;
        protected String description;
    
        protected Content(...) {
            ...
        }
        ...
    }
    ```

   저는 Content를 추상 클래스로 정의했는데, 이 선택의 근거가 맞는지 확인받고 싶습니다.

   제가 이해한 바로는, 일반 클래스가 객체 설계도라면 추상 클래스는 클래스에 추상화가 더해진 개념이라 추상적이기 때문에 객체로 만들 수 없습니다. 즉 추상 클래스의 목적은 해당 추상클래스로 직접 인스턴스 만들지 말고 상속받아서 사용하라는 의미를 코드 차원에서 강제하는 것이라고 생각했습니다.

   이 기준으로 보면, 넷플릭스 도메인에서 "Content" 그 자체로 존재하는 객체는 의미가 없다고 판단했습니다. 영화, 드라마, 다큐멘터리 같은 구체적인 종류만 실체가 있지, "그냥 Content"는 개념일 뿐이라고 생각했습니다. 일반 클래스로 두면 `new Content()`가 가능해지는데, 이 부분이 도메인 규칙에 어긋난다고 봤습니다. 그래서 추상 클래스로 선언해서 "Content는 개념이지 실체가 아니다"를 명시하고 싶었습니다.

   추가로 추상 클래스에서는 메서드를 `abstract`로 선언해 자식 클래스에서 구현을 강제할 수 있다는 것도 추상 클래스를 선택한 근거 중 하나였습니다. 일반 클래스에서는 오버라이딩이 의무가 아니지만, 추상 메서드는 반드시 구현체가 필요하다고 이해했습니다.

    - 구체적인 실체가 없고 항상 자식 형태로만 존재해야 하는 타입이라는 이유로 추상 클래스를 고르는 게 적절한 판단 기준이 맞나요?
    - 혹시 이 상황에서 인터페이스를 고려하지 않은 게 문제가 될 수 있을까요? 저는 공통 필드(id, name 등)와 생성자를 공유하고 싶어서 추상 클래스가 더 맞다고 생각했는데, 이 근거도 타당한지 궁금합니다.
2. 메뉴가 늘어날때마다 분기문이 늘어남

    ```java
    private static void printMenu() {
         System.out.println();
         System.out.println("─────────────── MENU ───────────────");
         System.out.println("  [1] 컨텐츠 출력");
         System.out.println("  [2] 컨텐츠 조회");
         System.out.println("  [3] 컨텐츠 추가");
         System.out.println("  [0] 종료");
         System.out.println("─────────────────────────────────────");
    }
    ```

   수업시간에 OCP에 대해 배웠는데, 지금 구조에서는 메뉴가 늘어날 때마다 `printMenu()`의 출력문과 메뉴 선택 처리의 `switch` 분기가 동시에 늘어납니다. OCP 관점에서 개선하고 싶은데 방법을 잘 모르겠습니다.

   Genre나 AgeRating 출력할 때도 비슷한 고민이 있었는데, 그때는 enum으로 정의한 뒤 `values()`로 순회해서 출력하는 방식으로 정적인 분기에서 어느 정도 벗어났습니다.

    ```java
    public enum Genre {
        ACTION(1, "액션"),
        COMEDY(2, "코미디"),
        ROMANCE(3, "로맨스"),
        ...
    
        private final int code;
        private final String label;
    
        ...
    
        public static Genre fromCode(int code) {
            for (Genre genre : values()) {
                if (genre.code == code) {
                    return genre;
                }
            }
            throw new IllegalArgumentException("Invalid Genre code: " + code);
        }
    }
    ```

    ```java
    //InputView.java
    private static Genre readGenre() {
            System.out.println();
            System.out.println("─── 장르 선택 ───");
            Genre[] genres = Genre.values();
            for (Genre genre : genres) {
                System.out.printf("  [%d] %s%n", genre.getCode(), genre);
            }
            int choice = readNumber("선택");
            if (choice < 1 || choice > genres.length) {
                throw new IllegalArgumentException("잘못된 장르입니다");
            }
            return Genre.fromCode(choice);
    }
    ```

   메뉴도 같은 방식으로 enum화하면 출력 부분은 해결될 것 같습니다. 하지만 메뉴는 항목마다 실행해야 할 동작이 다르기 때문에, 동작 분기까지 enum으로 처리해야 할지, 아니면 다른 패턴이 더 적절한지 잘 모르겠습니다.

    - 메뉴처럼 각 항목마다 실행할 동작이 다른 경우에도 enum이 좋은 선택인가요?
    - 콘솔 메뉴 같은 단순한 구조에서 OCP를 엄격히 지키는 것이 항상 좋은 선택일까요? 오히려 오버엔지니어링이 되는 경계가 있다면 어디일지 궁금합니다.
3. IdGenerator의 인스턴스화 가능

    ```java
    public class ContentIdGenerator {
    
        private static int counter = 1; // 동시성 고려 X
    
        private ContentIdGenerator() {
        }
    
        public static int generateId() {
            return counter++;
        }
    }
    ```

   이 클래스는 Content id 생성기 역할만 하는 유틸 클래스이기 때문에 인스턴스로 만들 이유가 없다고 생각했습니다. 그래서 생성자를 `private` 접근 제어자로 설정해 외부에서의 인스턴스 생성을 막았습니다.

    - 자바에서 유틸 클래스의 인스턴스화를 막을 때 `private` 생성자만으로 충분한가요, 아니면 더 권장되는 패턴이 있을까요?
4. int와 Integer

    ```java
     protected final int id;
     protected Integer runningTime;
    ```

   제가 생각했을 때 필드 관점에서 둘의 차이는 null 허용 여부라고 이해했습니다. 비즈니스 로직상 어떤 경우라도 null이 존재해선 안 되는 값은 primitive type으로, 그렇지 않다고 판단될 경우 래퍼 클래스로 정의했습니다.

   예를 들어 `id`는 식별자라서 null이 존재할 수 없다고 봤기 때문에 `int`로, `runningTime`은 도메인 관점에서 정보가 없을 수도 있는 값이라 판단해 `Integer`로 정의했습니다.

    - null 허용 여부를 기준으로 `int`와 `Integer`를 구분하는 게 적절한 판단 기준이 맞나요?
    - 만약 그렇다면, 입력 단계에서 항상 값을 받도록 강제하고 있는 경우에도 도메인 관점에서 null이 가능하다는 이유로 `Integer`를 쓰는 게 타당한가요? 아니면 실제로 null이 들어올 수 있을 때만 `Integer`를 써야 하나요?
    - 이 외에 `int`와 `Integer`를 선택할 때 추가로 고려해야 할 기준이 있을까요?

## 배운 점
이번 프로젝트에서 가장 크게 와닿은 건 추상 클래스의 의미였습니다. 단순히 인스턴스화를 막는 문법이 아니라, "Content는 개념이지 실체가 아니다"라는 도메인 규칙을 코드로 강제하는 수단이라는 것을 알게 됐습니다.
enum도 인식이 바뀐 부분입니다. 그저 상수 모음으로만 봤는데, Genre를 구현하면서 분기문을 줄이고 데이터와 동작을 함께 묶는 도구로 쓸 수 있다는 것을 경험했습니다.
계층 분리에서는 "출력은 누가 책임지는가" 같은 질문을 의식적으로 던지게 됐습니다. View와 도메인을 분리하면서, 책임을 어디에 둘 것인가에 대한 감각이 조금씩 생겼습니다.
마지막으로 OCP는 책에서만 보던 개념이 실제 코드에서 어떻게 어겨지는지 직접 마주했습니다. 메뉴가 늘어날 때마다 출력문과 분기문이 함께 늘어나는 구조를 보며, 원칙이 왜 중요하고 또 왜 지키기 어려운지를 동시에 느꼈습니다.