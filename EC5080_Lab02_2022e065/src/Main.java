import jdk.management.resource.internal.inst.StaticInstrumentation;

public class Main {
    public static void main(String[] arg){
        //  Create Multiple Book objects
        Book b1 = new Book(1, "\'Learn JAVA\'", "Kilshan p.", true);
        Book b2 = new Book(2, "\'Quick JAVA\'", "Gowtham J.", true);
        Book b3 = new Book(3, "\'Easy JAVA\'", "Renujan j.", false);

        // displays their book detail
        b1.displayBookDetails();
        b2.displayBookDetails();
        b3.displayBookDetails();

        // ............................Part 2..........................................
        System.out.println("..........Part 2................");
        // Create two reference pointing to the same object
        Book ref1 = b1;
        Book ref2 = b1;

        // Modify the book’s title
        ref1.setTitle("New Title 1");
        b1.displayBookDetails(); // Display the details after Modify
        ref2.setTitle("New Title 2");
        b1.displayBookDetails(); // Display the details after Modify

        // ...........................Part 3.............................................
        System.out.println("...........Part3...............");
        // creating a Library object
        Library l1 = new Library();

        // Add the above books to the library.
        l1.addBook(b1);
        l1.addBook(b2);
        l1.addBook(b3);

        // Remove a book from library
        l1.removeBook(2);

        // Display the library
        l1.displayAllBooks();

    }
}
