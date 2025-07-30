package com.example.crud_demo.banking.controller;

import com.example.crud_demo.banking.model.Banking;
import com.example.crud_demo.banking.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class BankingController {

    @Autowired
    private BankingService service;

    @GetMapping
    public Iterable<Banking> getAllAccounts() {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Optional<Banking> getAccountById(@PathVariable Long id) {
        return service.getAccount(id);
    }

    @PostMapping
    public Banking createAccount(@RequestBody Banking account) {
        return service.createOrUpdateAccount(account);
    }

    @PutMapping("/{id}")
    public Banking updateAccount(@PathVariable Long id, @RequestBody Banking account) {
        account.setAccountNumber(id);
        return service.createOrUpdateAccount(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        service.deleteAccount(id);
    }
}
