

public class Main {
    public static void main(String[] arg){
        //  Create Multiple Book objects
        Book b1 = new Book(1, "Learn JAVA", "Kilshan p.", true);
        Book b2 = new Book(2, "Quick JAVA", "Gowtham J.", true);
        Book b3 = new Book(3, "Easy JAVA", "Renujan j.", false);
        Book b4 = new Book(4, "Advanced JAVA", "Jaanu u.", true);

        BookCatalog catalogObj = new BookCatalog();
        BookGraph graphObj = new BookGraph();

        catalogObj.insert(b1);
        catalogObj.insert(b2);
        catalogObj.insert(b3);
        catalogObj.insert(b4);

        System.out.println("In-order Traversal of Book Catalog:");
        catalogObj.inOrderTraversal();

        graphObj.addRecommendation("Learn JAVA", "Easy JAVA");
        graphObj.addRecommendation("Quick JAVA", "Advanced JAVA");

        System.out.println("\nGraph Recommendations:");
        graphObj.displayRecommendations("Quick JAVA");

        System.out.println("\nDFS from Learn JAVA:");
        graphObj.dfs("Learn JAVA");

        // Remove a book and update graph
        System.out.println("\n\nRemoving 'Quick JAVA'...");
        catalogObj.remove("Quick JAVA");
        graphObj.removeBook("Quick JAVA");

        System.out.println("\nCatalog after remove:");
        catalogObj.inOrderTraversal();

        System.out.println("\nGraph Recommendations after remove:");
        graphObj.displayRecommendations("Advanced JAVA");

    }
}
