package models;

import enums.TYPE_TRANSACTION;

import java.time.LocalDate;

public class TransactionOperation {

    private TYPE_TRANSACTION typeTransaction;
    private double balanceTotal;
    private double amount;
    private LocalDate dateTransaction;


    public TransactionOperation(double amount, double balanceTotal, LocalDate dateTransaction, TYPE_TRANSACTION typeTransaction) {
        this.amount = amount;
        this.balanceTotal = balanceTotal;
        this.dateTransaction = dateTransaction;
        this.typeTransaction = typeTransaction;

    }

    public double getAmount() {
        return this.amount;
    }

    public double getBalanceTotal() {
        return this.balanceTotal;
    }

    public LocalDate getDateTransaction() {
        return this.dateTransaction;
    }

    public TYPE_TRANSACTION getTypeTransaction() {
        return this.typeTransaction;
    }
}
