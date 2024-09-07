package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "transactions")
@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userId;
    private int amount;
    private String transactionId;
    private String transactionType;
    @Embedded
    private Contact contact;
}
