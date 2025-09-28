import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class BookCatalogManager {

    static class BookCatalogException extends RuntimeException {
        public BookCatalogException(String message) {
            super(message);
        }
    }

    static class InvalidISBNException extends BookCatalogException {
        public InvalidISBNException(String message) {
            super(message);
        }
    }

    static class DuplicateISBNException extends BookCatalogException {
        public DuplicateISBNException(String message) {
            super(message);
        }
    }

    static class MalformedBookEntryException extends BookCatalogException {
        public MalformedBookEntryException(String message) {
            super(message);
        }
    }

    static class InsufficientArgumentsException extends BookCatalogException {
        public InsufficientArgumentsException(String message) {
            super(message);
        }
    }

    static class InvalidFileNameException extends BookCatalogException {
        public InvalidFileNameException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        int added = 0, searched = 0, errors = 0;

        try {
            if (args.length < 2) throw new InsufficientArgumentsException("Fewer than two arguments given");
            String fileName = args[0];

            if (!fileName.endsWith(".txt"))
                throw new InvalidFileNameException("First argument must be a .txt file");

            File catalogFile = new File(fileName);
            catalogFile.getParentFile().mkdirs();
            catalogFile.createNewFile();

            if (args[1].contains(":")) {
                // Add book mode
                try {
                    String[] parts = args[1].split(":");
                    if (parts.length < 4) throw new MalformedBookEntryException("Book entry missing fields");

                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String isbn = parts[2].trim();
                    String copiesStr = parts[3].trim();

                    if (!isValidISBN(isbn)) throw new InvalidISBNException("ISBN must be 13-digit numeric");
                    if (isDuplicateISBN(fileName, isbn)) throw new DuplicateISBNException("Duplicate ISBN found");

                    int copies = Integer.parseInt(copiesStr);
                    addBook(fileName, title, author, isbn, copies);
                    System.out.println("Book added successfully.");
                    added++;

                } catch (Exception e) {
                    logError(e.getMessage());
                    errors++;
                }

            } else if (args[1].matches("\\d{13}")) {
                // Search by ISBN
                try {
                    searched += searchByISBN(fileName, args[1]);
                } catch (Exception e) {
                    logError(e.getMessage());
                    errors++;
                }

            } else {
                // Search by title keyword
                try {
                    searched += searchByTitle(fileName, args[1]);
                } catch (Exception e) {
                    logError(e.getMessage());
                    errors++;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            logError(e.getMessage());
            errors++;
        } finally {
            System.out.println("Books added: " + added);
            System.out.println("Books found: " + searched);
            System.out.println("Errors encountered: " + errors);
            System.out.println("Thank you for using the Library Book Tracker.");
        }
    }

    public static boolean isValidISBN(String isbn)
    {
        return isbn.matches("\\d{13}");
    }

    public static boolean isDuplicateISBN(String fileName, String isbn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 3 && parts[2].trim().equals(isbn)) {
                    count++;
                }
            }
            return count > 0;
        }
    }

    public static void addBook(String fileName, String title, String author, String isbn, int copies) throws IOException {
        List<String> books = new ArrayList<>();
        String newEntry = String.format("%-30s : %-20s : %-13s : %d", title, author, isbn, copies);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) books.add(line);
        }

        books.add(newEntry);
        books.sort(Comparator.comparing(l -> l.split(":")[0].trim().toLowerCase()));

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String book : books) writer.println(book);
        }
    }

    public static int searchByTitle(String fileName, String keyword) throws IOException {
        int found = 0;
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b", Pattern.CASE_INSENSITIVE);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (pattern.matcher(line).find()) {
                    System.out.println(line);
                    found++;
                }
            }
        }

        if (found == 0) System.out.println("No books found with the keyword: " + keyword);
        return found;
    }

    public static int searchByISBN(String fileName, String isbn) throws IOException {
        int found = 0;
        String lastMatch = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 3 && parts[2].trim().equals(isbn)) {
                    lastMatch = line;
                    found++;
                }
            }
        }

        if (found > 1) throw new DuplicateISBNException("Multiple books found with the same ISBN");
        if (found == 1) System.out.println(lastMatch);
        else System.out.println("No book found with ISBN: " + isbn);
        return found;
    }

    public static void logError(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("errors.log", true))) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.printf("[%s] %s%n", timeStamp, message);
        } catch (IOException ignored) {
        }
    }
}
