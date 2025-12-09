import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    String writer;
    String name;
    String number;
    ArrayList<String> review;

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    private static final String FILE_NAME = "books.txt";

    public Book(String writer, String name, String number) {
        this.writer = writer;
        this.name = name;
        this.number = number;
        this.review = new ArrayList<>();
    }

    public void BookSave() {
        while (true) {
            System.out.println("-----------------");
            System.out.print("저자 (종료 0): ");
            String writer = sc.nextLine();
            if (writer.equals("0")) return;

            System.out.print("도서명: ");
            String book_name = sc.nextLine();
            System.out.print("도서 번호: ");
            String book_number = sc.nextLine();

            books.add(new Book(writer, book_name, book_number));
            System.out.println("도서가 저장되었습니다!");
        }
    }

    public void BookSearch() {
        while (true) {
            System.out.println("-----------------");
            System.out.print("검색할 도서명 입력 (종료 0): ");
            String keyword = sc.nextLine();
            if (keyword.equals("0")) return;

            boolean found = false;

            for (Book b : books) {
                if (b.name.contains(keyword)) {
                    System.out.println("==== 검색 결과 ====");
                    System.out.println("저자: " + b.writer);
                    System.out.println("도서명: " + b.name);
                    System.out.println("도서 번호: " + b.number);

                    if (b.review.isEmpty()) {
                        System.out.println("후기: 없음");
                    } else {
                        for (int r = 0; r < b.review.size(); r++) {
                            System.out.println("후기 " + (r + 1) + ": " + b.review.get(r));
                        }
                    }
                    found = true;
                }
            }

            if (!found) {
                System.out.println("검색 결과가 없습니다.");
            }
        }
    }

    public void BookReview() {
        System.out.println("-----------------");
        System.out.print("후기 작성할 도서명: ");
        String reviewBookName = sc.nextLine();

        Book SelectBook = null;

        for (Book b : books) {
            if (b.name.equals(reviewBookName)) {
                SelectBook = b;
                break;
            }
        }

        if (SelectBook == null) {
            System.out.println("해당 도서명을 찾을 수 없습니다.");
        } else {
            System.out.println("후기 입력 (종료: 빈칸 입력)");

            while (true) {
                String input = sc.nextLine();
                if (input.isEmpty()) break;

                SelectBook.review.add(input);
                System.out.println("후기가 추가되었습니다 (종료: 빈칸 입력)");
            }
        }
    }

    public static void saveBooks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                String reviewStr = String.join(";", b.review);
                bw.write(b.writer + "," + b.name + "," + b.number + "," + reviewStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생");
        }
    }

    public static void loadBooks() {

        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                Book b = new Book(data[0], data[1], data[2]);

                if (!data[3].isEmpty()) {
                    String[] reviews = data[3].split(";");
                    for (String r : reviews) b.review.add(r);
                }

                books.add(b);
            }

        } catch (IOException e) {
            System.out.println("파일 로드 중 오류 발생");
        }
    }
}
