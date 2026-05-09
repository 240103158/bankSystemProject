package ideaprojects.banksystembackend.DTO.response;

import ideaprojects.banksystembackend.Entity.Transaction;
import ideaprojects.banksystembackend.Entity.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private String type;
    private double amount;
    private String localDateTime;
    private String description;

    public TransactionDTO(double amount, String localDateTime, String description, String type) {
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public Transaction toEntity() {
        return new Transaction(
                TransactionType.valueOf(type),
                amount,
                LocalDateTime.parse(localDateTime),
                description
        );
    }
}
