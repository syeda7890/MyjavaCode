import java.util.*;

class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return title + " by " + author + " - Price: $" + price;
    }
}

class Customer {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;

    public Customer(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Other getter methods for phoneNumber and email

    public void choosePaymentMethod() {
        // Add payment method selection logic if needed
    }
}

public class OnlineEBookShop {
    private static List<Book> books = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Customer currentCustomer = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBooks();
        showWelcomeMessage();

        boolean loggedIn = false;

        while (!loggedIn) {
            int choice = showMainMenu();

            switch (choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    currentCustomer = login();
                    if (currentCustomer != null) {
                        loggedIn = true;
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        showBookCategories();
        chooseBook();
    }

    private static void initializeBooks() {
        books.add(new Book("Harry Potter", "J.K. Rowling", 20.0));
        books.add(new Book("Steve Jobs", "Walter Isaacson", 15.0));
        books.add(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", 18.0));
        // Add more books as needed
    }

    private static void showWelcomeMessage() {
        System.out.println("Welcome to the Online E-Book Shop!");
    }

    private static int showMainMenu() {
        System.out.println("1. Register an account");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void registerAccount() {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        Customer newCustomer = new Customer(name, phoneNumber, email, password);
        customers.add(newCustomer);
        System.out.println("Account registered successfully!");
    }

    private static Customer login() {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return customer;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
        return null;
    }

    private static void showBookCategories() {
        System.out.println("Available book categories:");

        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).getTitle());
        }
    }

    private static void chooseBook() {
        System.out.print("Choose a book category (enter the number): ");
        int categoryChoice = scanner.nextInt();

        if (categoryChoice < 1 || categoryChoice > books.size()) {
            System.out.println("Invalid choice. Exiting.");
            System.exit(0);
        }

        Book selectedBook = books.get(categoryChoice - 1);
        System.out.println("Selected book: " + selectedBook);

        // Customer decides to buy or go back
        System.out.print("Do you want to buy this book? (1. Yes / 2. Go back): ");
        int buyChoice = scanner.nextInt();

        if (buyChoice == 1) {
            purchaseBook(selectedBook);
        } else {
            System.out.println("Thank you for visiting. Goodbye!");
            System.exit(0);
        }
    }

    private static void purchaseBook(Book book) {
        System.out.print("Enter your address: ");
        String address = scanner.next();

        // Payment options
        System.out.println("Select a payment option:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Bkash");
        System.out.println("4. Nagad");

        int paymentChoice = scanner.nextInt();
        switch (paymentChoice) {
            case 1:
                System.out.println("Payment successful! Thank you for your purchase.");
                break;
            case 2:
                processCardPayment();
                break;
            case 3:
            case 4:
                processMobilePayment(paymentChoice);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(0);
        }

        System.out.println("Book details:\n" + book);
        System.out.println("Deliver to: " + address);
        System.out.println("Thank you for shopping with us, " + currentCustomer.getName() + "!");
        System.exit(0);
    }

    private static void processCardPayment() {
        System.out.println("Enter card details for verification (for demonstration purposes):");
        System.out.print("Card number: ");
        String cardNumber = scanner.next();
        System.out.print("Expiration date: ");
        String expirationDate = scanner.next();
        System.out.print("CVV: ");
        String cvv = scanner.next();

        // For demonstration purposes, we're not performing actual card verification.
        System.out.println("Card details verified. Payment successful!");
    }

    private static void processMobilePayment(int paymentChoice) {
        System.out.print("Enter your mobile number for verification (for demonstration purposes): ");
        String mobileNumber = scanner.next();

        // For demonstration purposes, we're not sending an actual verification code.
        System.out.println("Verification code sent to " + mobileNumber);

        System.out.print("Enter the verification code: ");
        String verificationCode = scanner.next();

        System.out.println("Verification successful! Payment completed.");
    }
}