OnlineBookReaderSystem
├── Library
├── PaymentService
├── SessionManager
│
├── User
│     ├── Reader
│     └── Admin
│
├── Book
├── Subscription
├── Session
├── Rating
└── Bookmark


// Main Classes

enum Role {
    READER,
    ADMIN
}

enum SubscriptionType {
    NONE,
    MONTHLY,
    YEARLY
}

// Book
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String id;
    private String title;
    private String author;
    private String category;
    private String content;

    private int totalReads;

    private List<Integer> ratings;

    public Book(String id,
                String title,
                String author,
                String category,
                String content) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.content = content;
        this.ratings = new ArrayList<>();
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {

        if (ratings.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (int rating : ratings) {
            sum += rating;
        }

        return (double) sum / ratings.size();
    }

    public void incrementReadCount() {
        totalReads++;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalReads() {
        return totalReads;
    }
}


// Subscription
import java.time.LocalDate;

public class Subscription {

    private SubscriptionType type;
    private LocalDate expiryDate;

    public Subscription(
            SubscriptionType type,
            LocalDate expiryDate) {

        this.type = type;
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return expiryDate.isAfter(LocalDate.now());
    }

    public SubscriptionType getType() {
        return type;
    }
}


// User

import java.util.HashSet;
import java.util.Set;

public abstract class User {

    protected String id;
    protected String name;

    protected Subscription subscription;

    protected Set<Book> savedBooks;

    public User(String id,
                String name) {

        this.id = id;
        this.name = name;

        this.savedBooks = new HashSet<>();
    }

    public void saveBook(Book book) {
        savedBooks.add(book);
    }

    public Set<Book> getSavedBooks() {
        return savedBooks;
    }

    public void setSubscription(
            Subscription subscription) {

        this.subscription = subscription;
    }

    public abstract boolean canRead();

    public abstract Role getRole();
}



// Reader


public class Reader extends User {

    public Reader(String id,
                  String name) {

        super(id, name);
    }

    @Override
    public boolean canRead() {

        return subscription != null &&
               subscription.isActive();
    }

    @Override
    public Role getRole() {
        return Role.READER;
    }
}


// Admin

public class Admin extends User {

    public Admin(String id,
                 String name) {

        super(id, name);
    }

    @Override
    public boolean canRead() {
        return true;
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}


// Session

import java.time.LocalDateTime;

public class Session {

    private User user;

    private LocalDateTime loginTime;

    public Session(User user) {

        this.user = user;
        this.loginTime = LocalDateTime.now();
    }

    public boolean isExpired() {

        return loginTime
                .plusHours(24)
                .isBefore(LocalDateTime.now());
    }

    public User getUser() {
        return user;
    }
}


// SessionManager

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private Map<String, Session> sessions =
            new HashMap<>();

    public void login(
            String userId,
            User user) {

        sessions.put(
                userId,
                new Session(user)
        );
    }

    public boolean isLoggedIn(
            String userId) {

        Session session =
                sessions.get(userId);

        return session != null &&
               !session.isExpired();
    }
}

// PaymentService

import java.time.LocalDate;

public class PaymentService {

    public Subscription buyMonthly() {

        return new Subscription(
                SubscriptionType.MONTHLY,
                LocalDate.now().plusMonths(1)
        );
    }

    public Subscription buyYearly() {

        return new Subscription(
                SubscriptionType.YEARLY,
                LocalDate.now().plusYears(1)
        );
    }
}

// Library

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books =
            new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchByTitle(
            String title) {

        for (Book book : books) {

            if (book.getTitle()
                    .equalsIgnoreCase(title)) {

                return book;
            }
        }

        return null;
    }

    public List<Book> searchByCategory(
            String category) {

        List<Book> result =
                new ArrayList<>();

        for (Book book : books) {

            if (book.getCategory()
                    .equalsIgnoreCase(category)) {

                result.add(book);
            }
        }

        return result;
    }

    public List<Book> getBooks() {
        return books;
    }
}


// OnlneBookReaderSystem

public class OnlineBookReaderSystem {

    private Library library;
    private PaymentService paymentService;
    private SessionManager sessionManager;

    public OnlineBookReaderSystem() {

        this.library = new Library();

        this.paymentService =
                new PaymentService();

        this.sessionManager =
                new SessionManager();
    }

    public void uploadBook(
            User user,
            Book book) {

        if (user.getRole() != Role.ADMIN) {

            throw new RuntimeException(
                    "Only admins can upload books"
            );
        }

        library.addBook(book);
    }

    public void readBook(
            User user,
            Book book) {

        if (!user.canRead()) {

            throw new RuntimeException(
                    "Subscription required"
            );
        }

        book.incrementReadCount();

        System.out.println(
                book.getContent()
        );
    }

    public void rateBook(
            Book book,
            int rating) {

        book.addRating(rating);
    }

    public Library getLibrary() {
        return library;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}

// Driver Code

public class Main {

    public static void main(String[] args) {

        OnlineBookReaderSystem system =
                new OnlineBookReaderSystem();

        Admin admin =
                new Admin("1", "John");

        Reader reader =
                new Reader("2", "Alice");

        Book book =
                new Book(
                        "101",
                        "Design Patterns",
                        "GoF",
                        "Software",
                        "Book Content..."
                );

        system.uploadBook(admin, book);

        Subscription subscription =
                system.getPaymentService()
                      .buyMonthly();

        reader.setSubscription(
                subscription
        );

        system.readBook(reader, book);

        reader.saveBook(book);

        system.rateBook(book, 5);

        System.out.println(
                book.getAverageRating()
        );
    }
}