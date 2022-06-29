package com.blockapp.service.impl;

import com.blockapp.entity.Transaction;
import com.blockapp.entity.User;
import com.blockapp.repository.TransactionRepository;
import com.blockapp.repository.UserRepository;
import com.blockapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Transaction save(Transaction transaction) throws Exception {
        User sender = transaction.getSender();
        BigDecimal amountS = sender.getAmount();
        amountS = amountS.subtract(transaction.getAmount());
        sender.setAmount(amountS);
        userRepository.save(sender);

        User receiver = transaction.getReceiver();
        BigDecimal amountR = receiver.getAmount();
        amountR = amountR.add(transaction.getAmount());
        receiver.setAmount(amountR);
        userRepository.save(receiver);

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

    @Override
    public List<Transaction> findBySenderId(Long senderId) throws Exception {
        return transactionRepository.findBySenderId(senderId);
    }

    @Override
    public List<Transaction> findByReceiverId(Long receiverId) throws Exception {
        return transactionRepository.findByReceiverId(receiverId);
    }

    @Override
    public List<Transaction> findByUserId(Long userId) throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> transactionsLikeSender = transactionRepository.findBySenderId(userId);
        List<Transaction> transactionsLikeReceiver = transactionRepository.findByReceiverId(userId);
        transactions.addAll(transactionsLikeSender);
        transactions.addAll(transactionsLikeReceiver);
        return transactions;
    }
}
