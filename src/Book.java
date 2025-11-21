import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    String writer;
    String name;
    String number;
    ArrayList<String> review;
    Scanner sc;
    ArrayList<Book> books;

    public Book(String writer, String name, String number) {
        this.sc = new Scanner(System.in);
        this.books = new ArrayList();
        this.writer = writer;
        this.name = name;
        this.number = number;
        this.review = new ArrayList();
    }

    // 책 정보 저장 1번 기능
    public void BookSave() {
        while(true) {
            System.out.println("-----------------");
            System.out.print("저자 (종료 0): ");
            String writer = this.sc.nextLine();
            if (writer.equals("0")) {
                return;
            }

            System.out.print("도서명: ");
            String book_name = this.sc.nextLine();
            System.out.print("도서 번호: ");
            String book_number = this.sc.nextLine();
            this.books.add(new Book(writer, book_name, book_number));
            System.out.println("도서가 저장되었습니다!");
        }
    }

    //책 검색 & 출력 2번 기능
    public void BookSearch() {
        while(true) {
            System.out.println("-----------------");
            System.out.print("검색할 도서명 입력 (종료 0): ");
            String keyword = this.sc.nextLine();
            if (keyword.equals("0")) {
                return;
            }

            boolean found = false;

            for(Book b : this.books) {
                if (b.name.contains(keyword)) {
                    System.out.println("==== 검색 결과 ====");
                    System.out.println("저자: " + b.writer);
                    System.out.println("도서명: " + b.name);
                    System.out.println("도서 번호: " + b.number);
                    if (b.review.isEmpty()) {
                        System.out.println("후기: 없음");
                    } else {
                        for(int i = 0; i < b.review.size(); ++i) {
                            System.out.println("후기 " + (i + 1) + ": " + (String)b.review.get(i));
                        }
                    }

                    found = true;
                }

                if (!found) {
                    System.out.println("검색 결과가 없습니다.");
                }
            }
        }
    }

    //책 후기 저장 3번 기능
    public void BookReview() {
        System.out.println("-----------------");
        System.out.print("후기 작성할 도서명: ");
        String reviewBookName = this.sc.nextLine();
        Book SelectBook = null;

        for(Book b : this.books) {
            if (b.name.equals(reviewBookName)) {
                SelectBook = b;
                break;
            }
        }

        if (SelectBook == null) {
            System.out.println("해당 도서명을 찾을 수 없습니다.");
        } else {
            System.out.println("후기 입력 (종료 여백 입력):");

            while(true) {
                String input = this.sc.nextLine();
                if (input.isEmpty()) {
                    break;
                }

                SelectBook.review.add(input);
                System.out.println("후기가 추가되었습니다 (종료 여백 입력):");
            }
        }

    }
}
