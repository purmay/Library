import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    String writer;
    String name;
    String number;
    String plot;

    public Book(String writer, String name, String number, String plot) {
        this.writer = writer;
        this.name = name;
        this.number = number;
        this.plot = plot;
    }

    public void booksave(){

    }

    public void display(String suffix) {
        System.out.println("저자: " + writer);
        System.out.println("도서명: " + name + suffix);
        System.out.println("도서 번호: " + number);
        System.out.println("줄거리: " + plot);

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();

        while (true) {
            System.out.println("--------------------------------");
            System.out.println("1. 도서 정보 저장");
            System.out.println("2. 도서 검색");
            System.out.println("3. 도서 후기");
            System.out.println("4. 종료");
            System.out.print("입력: ");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println("--------------------------------");

            if (option == 1) {
                System.out.print("저자: ");
                String writer = sc.nextLine();

                System.out.print("도서명: ");
                String book_name = sc.nextLine();

                System.out.print("도서 번호: ");
                String book_number = sc.nextLine();

                System.out.print("줄거리: ");
                String plot = sc.nextLine();

                books.add(new Book(writer, book_name, book_number, plot));
                System.out.println("도서가 저장되었습니다!");

            } else if (option == 2) {
                System.out.print("검색할 도서명 입력: ");
                String keyword = sc.nextLine();
                boolean found = false;

                for (Book b : books) {
                    if (b.name.contains(keyword)) {
                        System.out.println("==== 검색 결과 ====");
                        System.out.println("저자: " + b.writer);
                        System.out.println("도서명: " + b.name);
                        System.out.println("도서 번호: " + b.number);
                        System.out.println("줄거리: " + b.plot);
                        found = true;
                    }
                }

                if (!found) System.out.println("검색 결과가 없습니다.");

            } else if (option == 3) {
                System.out.println("아직 구현되지 않은 기능입니다.");

            } else if (option == 4) {
                System.out.println("종료합니다.");
                break;

            } else {
                System.out.println("지원하지 않는 기능입니다.");
            }
        }
    }
}
