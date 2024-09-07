package org.example.service;

import org.example.models.Transaction;
import org.example.models.TransactionDto;
import org.example.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto getTransactionDetails(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if(transaction == null) {
            return null;
        }
        return new TransactionDto(transaction.getId(), transaction.getUserId(), transaction.getAmount(), transaction.getTransactionId(),transaction.getTransactionType(),transaction.getContact());
    }

    public TransactionDto createTransaction(TransactionDto request) {
        String userId = request.getUserId();
        if(transactionRepository.existsByUserId(userId)) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setUserId(request.getUserId());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionId(request.getTransactionId());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setContact(request.getContact());
        transactionRepository.save(transaction);
        return new TransactionDto(transaction.getId(), transaction.getUserId(), transaction.getAmount(), transaction.getTransactionId(),transaction.getTransactionType(), transaction.getContact());
    }
}
