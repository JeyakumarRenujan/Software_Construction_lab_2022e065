// EC5080: Software Construction
// Lab 02
// @author : 2022/E/065

class Book {
    // Attributes
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor to initialize book details.
    public Book(int id, String title, String author, boolean isAvailable){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    // getter method for id
    public int getID(){
        return id;
    }

    // setter method for title
    public void setTitle(String title){
        this.title = title;
    }

    // Method that prints book information.
    public void displayBookDetails(){
        System.out.println("ID: " + id + "\nTITLE: " + title + "\nAUTHOR: " + author + "\nAVAILABLE: " + isAvailable);
        System.out.println();
    }




}
