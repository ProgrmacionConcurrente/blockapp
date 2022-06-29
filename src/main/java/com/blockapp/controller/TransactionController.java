package com.blockapp.controller;

import com.blockapp.entity.Transaction;
import com.blockapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        try {
            List<Transaction> transactions = transactionService.getAll();
            if (transactions.size() > 0)
                return new ResponseEntity<>(transactions, HttpStatus.OK);
            else
                return new ResponseEntity<>(transactions, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/{transactionId}"})
    public ResponseEntity<Transaction> findById(@PathVariable("transactionId") Long transactionId) {
        try {
            Optional<Transaction> transaction = transactionService.getById(transactionId);
            if (!transaction.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(transaction.get(),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody Transaction transaction) {
        try {
            Transaction transactionNew = transactionService.save(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionNew);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
