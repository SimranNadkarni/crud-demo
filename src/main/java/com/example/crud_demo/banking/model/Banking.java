package com.example.crud_demo.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Banking {

    @Id
    private Long accountNumber;

    private String accountHolder;
    private Double balance;


    public Banking() {}

    public Banking(Long accountNumber, String accountHolder, Double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

  
    public Long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountHolder() { return accountHolder; }
    public void setAccountHolder(String accountHolder) { this.accountHolder = accountHolder; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
