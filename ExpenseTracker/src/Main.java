import model.Expense;
import service.ExpenseService;
import service.UserService;
import util.FileUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();

        // Load expenses from file
        List<Expense> expenses = FileUtil.loadExpenses();
        expenses.forEach(expenseService::addExpense);

        System.out.println("Welcome to Expense Tracker!");

        while (true) {
            System.out.println("1. Register\n2. Login\n3. Add Expense\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    userService.registerUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (userService.loginUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                        // Expense management menu
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                    break;
                case 3:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    expenseService.addExpense(category, amount, date);
                    break;
                case 4:
                    // Save expenses to file before exiting
                    List<Expense> expensesToSave = expenseService.getExpenses();
                    System.out.println("Expenses to save: " + expensesToSave); // Debug statement
                    FileUtil.saveExpenses(expensesToSave);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}