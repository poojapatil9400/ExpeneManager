package util;

import model.Expense;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String EXPENSE_FILE = "D:\\JavaRevision\\Motion_cut\\ExpenseTracker\\expenses.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void saveExpenses(List<Expense> expenses) {
        File file = new File(EXPENSE_FILE);
        System.out.println("Saving expenses to: " + file.getAbsolutePath()); // Debug statement
        file.getParentFile().mkdirs(); // Create the "data" folder if it doesn't exist
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Expense expense : expenses) {
                writer.write(expense.getCategory() + "," + expense.getAmount() + "," + expense.getDate().format(DATE_FORMATTER));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EXPENSE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[0];
                double amount = Double.parseDouble(parts[1]);
                LocalDate date = LocalDate.parse(parts[2], DATE_FORMATTER);
                expenses.add(new Expense(category, amount, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}