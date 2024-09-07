package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.models.TransactionDto;
import org.example.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto request) {
        TransactionDto transactionDto = transactionService.createTransaction(request);
        if (transactionDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @Operation(summary = "Get transaction details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = @Content)
    })
    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionDto> getTransactionDetails(@PathVariable("id") Long transactionId) {

        TransactionDto transactionDto = transactionService.getTransactionDetails(transactionId);
        if(transactionDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(transactionDto, HttpStatus.OK);
        }
    }
}

