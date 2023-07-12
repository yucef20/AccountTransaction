package models;

import enums.TYPE_TRANSACTION;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountVO {
    private ArrayList<TransactionOperation> transactions;
    private double balance;

    public AccountVO(double balance) {
        this.balance = balance;
        this.transactions = new ArrayList<TransactionOperation>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void depositAmount(double amount) {
        this.balance +=amount;
        this.transactions.add(new TransactionOperation(amount,this.balance, LocalDate.now(), TYPE_TRANSACTION.DEPOSIT));
    }

    public void withDrawAmount(double amount) {
        this.balance -=amount;
        this.transactions.add(new TransactionOperation(-amount,this.balance, LocalDate.now(), TYPE_TRANSACTION.WITH_DRAW));

    }

    public List<TransactionOperation> getTransactions() {
        return this.transactions;
    }

    public void printHistoryTransactionsTest() {
        String Header = "DATE_TRANSACTION\t\tTYPE_TRANSACTION\tAMOUNT_MONEY\tBALANCE_OF_ACCOUNT";
        System.out.println(Header);
        for (TransactionOperation transaction : transactions) {
            System.out.println( transaction.getDateTransaction() + "\t" +transaction.getTypeTransaction() + "\t" + transaction.getAmount()+"\t" + transaction.getBalanceTotal());
        }
    }
}
