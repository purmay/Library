import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Book> books = new ArrayList<>();
        Book myBook = new Book("null", "null", "null", "null");
        books.add(myBook);
        myBook.display("!");
    }
}