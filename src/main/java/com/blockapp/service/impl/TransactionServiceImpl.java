package com.blockapp.service.impl;

import com.blockapp.entity.Transaction;
import com.blockapp.repository.TransactionRepository;
import com.blockapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) throws Exception {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getById(Long id) throws Exception {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAll() throws Exception {
        return transactionRepository.findAll();
    }

    @Override
    public void delete(Long id) throws Exception {
        transactionRepository.deleteById(id);
    }
}
