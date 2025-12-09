import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Book myBook = new Book("null", "null", "null");

        while (true) {
            System.out.println("-----------------");
            System.out.println("1. 도서 정보 저장");
            System.out.println("2. 도서 검색");
            System.out.println("3. 도서 후기");
            System.out.println("4. 도서 저장");
            System.out.println("5. 도서 불러오기");
            System.out.println("0. 종료");
            System.out.print("입력: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                myBook.BookSave();
            } else if (option == 2) {
                myBook.BookSearch();
            } else if (option == 3) {
                myBook.BookReview();
            } else if (option == 4) {
                Book.saveBooks();
                System.out.println("도서 저장을 완료했습니다.");
            } else if (option == 5) {
                Book.loadBooks();
                System.out.println("도서 불러오기가 완료 되었습니다.");
            } else if (option == 0) {
                System.out.println("종료합니다.");



                break;
            } else {
                System.out.println("지원하지 않는 기능입니다.");
            }
        }
    }
}
