import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystemMain {
    public static void main(String[] args) {
        LibraryManagementSystem system = new LibraryManagementSystem();
        system.initializeLibrary();
        system.handleUserInput();
    }
}

interface BookOperations {
    void borrow();
    void returnBook();
    void displayDetails();
}

abstract class AbstractBook implements BookOperations {
    protected String title;
    protected String author;
    protected String isbn;
    protected boolean isAvailable;

    // Constructor
    public AbstractBook(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; 
    }

    public String getTitle() {
        return title;
    }

    public abstract void displayDetails();
}

class Book extends AbstractBook {

    public Book(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You successfully borrowed \"" + title + "\".");
        } else {
            System.out.println("Sorry, \"" + title + "\" is currently not available.");
        }
    }

    @Override
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You successfully returned \"" + title + "\".");
        } else {
            System.out.println("\"" + title + "\" was not borrowed.");
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}

class Library {
    private List<AbstractBook> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(AbstractBook book) {
        books.add(book);
        System.out.println("Book \"" + book.getTitle() + "\" added to the library.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n--- Library Books ---");
        for (AbstractBook book : books) {
            book.displayDetails();
            System.out.println();
        }
    }

    public AbstractBook searchBookByTitle(String title) {
        for (AbstractBook book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem() {
        library = new Library();
    }

    public void initializeLibrary() {
        library.addBook(new Book("Shrimad Bhagavad Gita", "Vyas", "1234567890"));
        library.addBook(new Book("Shyamchi Aai", "Sane Guruji", "0987654321"));
    }

    public void displayMenu() {
        System.out.println("\n--- Library Menu ---");
        System.out.println("1. Display All Books");
        System.out.println("2. Borrow a Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Exit");
    }

    public void handleUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                displayMenu();
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    System.out.print("Enter your choice: ");
                    scanner.next(); 
                }
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        library.displayAllBooks();
                        break;
                    case 2:
                        System.out.print("Enter the title of the book to borrow: ");
                        String borrowTitle = scanner.nextLine();
                        AbstractBook bookToBorrow = library.searchBookByTitle(borrowTitle);
                        if (bookToBorrow != null) {
                            bookToBorrow.borrow();
                        } else {
                            System.out.println("Book not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the title of the book to return: ");
                        String returnTitle = scanner.nextLine();
                        AbstractBook bookToReturn = library.searchBookByTitle(returnTitle);
                        if (bookToReturn != null) {
                            bookToReturn.returnBook();
                        } else {
                            System.out.println("Book not found.");
                        }
                        break;
                    case 4:
                        System.out.println("Exiting the Library Management System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 4);
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }
}