package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Integer id;
    private String userId;
    private int amount;
    private String transactionId;
    private String transactionType;
    @JsonProperty("contact")
    private Contact contact;

    public Contact getContact() {
        return contact;
    }
}
