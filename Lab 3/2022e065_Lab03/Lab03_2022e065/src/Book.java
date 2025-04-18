import java.util.*;

// EC5080: Software Construction
// Lab 03
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
    // getter method for tittle
    public String getTitle(){
        return title;
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

// Part 1:
class BookNode {
    Book book;
    BookNode left, right;

    BookNode(Book book) {
        this.book = book;
    }
}

class BookCatalog {
    BookNode root;

    public void insert(Book book) {
        root = insertRec(root, book);
    }
    public BookNode insertRec(BookNode root, Book book) {
        if (root == null)
            return new BookNode(book);
        if (book.getTitle().compareTo(root.book.getTitle()) < 0)
            root.left = insertRec(root.left, book);
        else
            root.right = insertRec(root.right, book);
        return root;
    }

    public Book search(String title) {
        return searchRec(root, title);
    }

    public Book searchRec(BookNode root, String title) {
        if (root == null)
            return null;
        else if (title.equals(root.book.getTitle()))
            return root.book;
        else if (title.compareTo(root.book.getTitle()) < 0)
            return searchRec(root.left, title);
        else
            return searchRec(root.right, title);
    }

    public void inOrderTraversal() {
        inOrderRec(root);
    }

    public void inOrderRec(BookNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.book);
            inOrderRec(root.right);
        }
    }

    public BookNode remove(BookNode root, String title) {
        if (root == null) return null;
        if (title.compareTo(root.book.getTitle()) < 0)
            root.left = remove(root.left, title);
        else if (title.compareTo(root.book.getTitle()) > 0)
            root.right = remove(root.right, title);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.book = minValue(root.right);
            root.right = remove(root.right, root.book.getTitle());
        }
        return root;
    }

    public Book minValue(BookNode root) {
        Book min = root.book;
        while (root.left != null) {
            min = root.left.book;
            root = root.left;
        }
        return min;
    }

    public void remove(String title) {
        root = remove(root, title);
    }
}


// Part 2:
class BookGraph {
    Map<String, List<String>> adjList = new HashMap<>();

    public void addRecommendation(String book1, String book2) {
        adjList.putIfAbsent(book1, new ArrayList<>());
        adjList.putIfAbsent(book2, new ArrayList<>());
        adjList.get(book1).add(book2);
        adjList.get(book2).add(book1);
    }

    public void displayRecommendations(String book) {
        List<String> recs = adjList.get(book);
        if (recs != null) {
            System.out.println("Recommendations for " + book + ": " + recs);
        } else {
            System.out.println("No recommendations for " + book);
        }
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(start, visited);
    }

    private void dfsHelper(String book, Set<String> visited) {
        if (!visited.contains(book)) {
            visited.add(book);
            System.out.print(book + " -> ");
            for (String neighbor : adjList.getOrDefault(book, new ArrayList<>())) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void removeBook(String book) {
        adjList.remove(book);
        for (List<String> neighbors : adjList.values()) {
            neighbors.remove(book);
        }
    }
}
