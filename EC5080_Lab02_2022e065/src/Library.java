
import java.util.ArrayList;

class Library {
    ArrayList<Book> books = new ArrayList<>();

    // Method to add a book to the library
    public void addBook(Book book){
        books.add(book);
    }

    // Method to remove a book from the library
    public void removeBook(int id){
        for (Book i : books){
            if (i.getID() == id){
                books.remove(i);
            }
            else {
                System.out.println("Book ID " + id + " not found.");
            }
        }
    }

    // Method to display all books in the library
    public void displayAllBooks(){
        if (books.isEmpty()){
            System.out.println("library is empty.");
        }
        else{
            for (Book i : books){
                i.displayBookDetails();
            }
        }
    }

}
