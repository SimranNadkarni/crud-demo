package com.example.crud_demo.banking.repository;

import com.example.crud_demo.banking.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankingRepository extends CrudRepository<Banking, Long> {
    
}
