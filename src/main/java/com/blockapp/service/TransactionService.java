package com.blockapp.service;

import com.blockapp.entity.Transaction;

import java.util.List;

public interface TransactionService extends CrudService<Transaction> {
    public List<Transaction> findBySenderId(Long senderId) throws Exception;
    public List<Transaction> findByReceiverId(Long receiverId) throws Exception;
}
