package com.blockapp.controller;

import com.blockapp.entity.Transaction;
import com.blockapp.service.TransactionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
            @ApiResponse(responseCode = "404", description ="Transacciones no encontradas")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transaccion encontrada"),
            @ApiResponse(responseCode = "404", description ="Transaccion no encontrada")
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transaccion creada"),
            @ApiResponse(responseCode = "404", description ="Transaccion no creada")
    })
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody Transaction transaction) {
        try {
            Transaction transactionNew = transactionService.save(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionNew);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByUserId/{userId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
            @ApiResponse(responseCode = "404", description ="Transacciones no encontradas")
    })
    public ResponseEntity<List<Transaction>> findByUserId(@PathVariable("userId") Long userId)
    {
        try {
            List<Transaction> transactionsByUser = transactionService.findByUserId(userId);
            System.out.println("Transactions by User"+ transactionsByUser);
            if(transactionsByUser.size()>0)
                return new ResponseEntity<>(transactionsByUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/searchBySenderId/{senderId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
            @ApiResponse(responseCode = "404", description ="Transacciones no encontradas")
    })
    public ResponseEntity<List<Transaction>> findBySenderId(@PathVariable("senderId") Long senderId)
    {
        try {
            List<Transaction> transactionsBySender = transactionService.findBySenderId(senderId);
            System.out.println("Transactions by Sender"+ transactionsBySender);
            if(transactionsBySender.size()>0)
                return new ResponseEntity<>(transactionsBySender, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchByReceiverId/{receiverId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transacciones encontradas"),
            @ApiResponse(responseCode = "404", description ="Transacciones no encontradas")
    })
    public ResponseEntity<List<Transaction>> findByReceiverId(@PathVariable("receiverId") Long receiverId)
    {
        try {
            List<Transaction> transactionsByReceiver = transactionService.findByReceiverId(receiverId);
            System.out.println("Transactions by Receiver"+ transactionsByReceiver);
            if(transactionsByReceiver.size()>0)
                return new ResponseEntity<>(transactionsByReceiver, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
