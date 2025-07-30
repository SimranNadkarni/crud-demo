package com.example.crud_demo.banking.service;

import com.example.crud_demo.banking.exception.InvalidBalanceException;
import com.example.crud_demo.banking.model.Banking;
import com.example.crud_demo.banking.repository.BankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankingService {

    @Autowired
    private BankingRepository repository;

    public Iterable<Banking> getAllAccounts() {
        return repository.findAll();
    }

    public Optional<Banking> getAccount(Long accountNumber) {
        return repository.findById(accountNumber);
    }


    public void deleteAccount(Long accountNumber) {
        repository.deleteById(accountNumber);
    }

    public Banking createOrUpdateAccount(Banking account) {
        if (account.getBalance() < 0) {
            throw new InvalidBalanceException("Balance cannot be negative.");
        }
        return repository.save(account);
    }
}
