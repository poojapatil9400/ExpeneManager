package service;

import model.Expense;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();

    // Method to add an expense using individual fields
    public void addExpense(String category, double amount, LocalDate date) {
        expenses.add(new Expense(category, amount, date)); // This should now work
        System.out.println("Expense added successfully!");
    }

    // Method to add an Expense object directly
    public void addExpense(Expense expense) {
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    // Method to get all expenses
    public List<Expense> getExpenses() {
        return expenses;
    }

    // Method to list all expenses
    public void listExpenses() {
        for (Expense expense : expenses) {
            System.out.println("Category: " + expense.getCategory() +
                               ", Amount: " + expense.getAmount() +
                               ", Date: " + expense.getDate());
        }
    }

    // Method to calculate total expenses by category
    public double getTotalExpensesByCategory(String category) {
        return expenses.stream()
                       .filter(e -> e.getCategory().equalsIgnoreCase(category))
                       .mapToDouble(Expense::getAmount)
                       .sum();
    }
}