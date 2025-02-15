package model;

import java.time.LocalDate;

public class Expense {
    private String category;
    private double amount;
    private LocalDate date;

    // Constructor
    public Expense(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}