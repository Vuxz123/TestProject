package com.ethnicthv;

public class Main {
    public static void main(String[] args){
        Account account = new Account();
        account.addTransaction(2000.255,"deposit");
        account.addTransaction(2000,"withdraw");
        account.addTransaction(-100,"deposit");
        account.printTransaction();
    }
}
