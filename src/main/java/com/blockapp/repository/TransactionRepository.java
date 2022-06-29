package com.blockapp.repository;

import com.blockapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findBySenderId(Long senderId);
    public List<Transaction> findByReceiverId(Long receiverId);
}
