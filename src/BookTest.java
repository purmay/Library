import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    Book testBook;

    @BeforeEach
    void setUp() {
        // 테스트 시작 전 Book 객체와 데이터 초기화
        Book.books.clear();  // 전역 리스트 초기화
        testBook = new Book("테스트저자", "테스트도서", "001");
        Book.books.add(testBook);
    }

    @Test
    @DisplayName("Book 생성자 동작 테스트")
    void testBookConstructor() {
        assertEquals("테스트저자", testBook.writer);
        assertEquals("테스트도서", testBook.name);
        assertEquals("001", testBook.number);
        assertTrue(testBook.review.isEmpty(), "리뷰 리스트는 비어 있어야 함");
    }

    @Test
    @DisplayName("후기 추가 기능 테스트")
    void testAddReviewManually() {
        testBook.review.add("정말 재미있어요!");
        testBook.review.add("다시 읽고 싶습니다.");

        assertEquals(2, testBook.review.size(), "리뷰 개수 확인");
        assertTrue(testBook.review.get(0).contains("재미"));
    }

    @Test
    @DisplayName("파일 저장 및 불러오기 테스트")
    void testSaveAndLoadBooks() {
        // 1. 파일 저장
        Book.saveBooks();

        File file = new File("books.txt");
        assertTrue(file.exists(), "books.txt 파일이 생성되어야 함");

        // 2. 기존 리스트 초기화 후 파일에서 다시 로드
        Book.books.clear();
        Book.loadBooks();

        // 3. 불러온 데이터 검증
        assertEquals(1, Book.books.size(), "불러온 도서 개수 확인");
        Book loadedBook = Book.books.get(0);
        assertEquals("테스트저자", loadedBook.writer);
        assertEquals("테스트도서", loadedBook.name);
        assertEquals("001", loadedBook.number);
    }

    @Test
    @DisplayName("리뷰 저장 후 복원 테스트")
    void testSaveAndLoadReviews() {
        // 1. 후기 추가 후 저장
        testBook.review.add("첫번째 리뷰");
        testBook.review.add("두번째 리뷰");
        Book.saveBooks();

        // 2. 다시 불러오기
        Book.books.clear();
        Book.loadBooks();

        // 3. 리뷰 복원 확인
        Book loaded = Book.books.get(0);
        assertEquals(2, loaded.review.size(), "리뷰 개수 확인");
        assertTrue(loaded.review.contains("첫번째 리뷰"));
    }

    @AfterEach
    void tearDown() {
        // 테스트 종료 후 임시 파일 정리
        File file = new File("books.txt");
        if (file.exists()) file.delete();
    }
}
