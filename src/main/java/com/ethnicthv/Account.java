package com.ethnicthv;

import java.util.ArrayList;
import java.util.List;

public class Account {

    public static class Transaction {
        private double balance;
        private TransactionType type;

        public Transaction(double balance, String type){
            this.balance = balance;
            this.type = TransactionType.fromString(type);
        }
        public void print(){
            String out = String.format("%.2f", this.balance);
            switch (this.type){
                case WITHDRAW -> out = "-" + out;
                case DEPOSIT -> out = "+" + out;
            }
            System.out.println(out);
        }
    }

    private interface TransactionFuction{
        void run(Account account, Double balance);
    }

    public enum TransactionType {
        WITHDRAW("withdraw", (account,balance) -> {
            if(balance < 0) {
                System.out.println("So tien ban nap vao khong hop le!");
                return;
            }
            if(balance > account.balance){
                System.out.println("So tien ban rut vuot qua so du!");
                return;
            }
            Transaction trans = new Transaction(balance,"withdraw");
            account.balance = account.balance - balance;
            account.addTransaction(trans);
        })
        ,
        DEPOSIT("deposit",(account,balance) -> {
            if(balance < 0) {
                System.out.println("So tien ban nap vao khong hop le!");
                return;
            }
            Transaction trans = new Transaction(balance,"deposit");
            account.balance = account.balance + balance;
            account.addTransaction(trans);
        })
        ,
        ILLEGAL("illegal",(account,balance) -> {
            System.out.println("Yeu cau khong hop le!");
        });
        private String name;
        private TransactionFuction func;
        TransactionType(String name, TransactionFuction func){
            this.name = name;
            this.func = func;
        }

        public static TransactionType fromString(String name){
            switch (name){
                case "deposit" -> {
                    return TransactionType.DEPOSIT;
                }
                case "withdraw" -> {
                    return TransactionType.WITHDRAW;
                }
                default -> {
                    return TransactionType.ILLEGAL;
                }
            }
        }

        public TransactionFuction getFunc(){
            return func;
        }
    }

    private double balance;
    private List<Transaction> transactionhis;

    public Account (){
        transactionhis = new ArrayList<>();
        balance = 0;
    }

    public Account (double amount) {
        transactionhis = new ArrayList<>();
        balance = amount;
    }

    private void addTransaction(Transaction transaction){
        transactionhis.add(transaction);
    }

    public void addTransaction(double balance, String operator){
        TransactionType type = TransactionType.fromString(operator);
        type.getFunc().run(this,balance);
    }

    public void printTransaction(){
        for (Transaction trans: transactionhis) {
            trans.print();
        }
    }

}
