package ideaprojects.banksystembackend.Entity;

import ideaprojects.banksystembackend.DTO.response.TransactionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "sender_account_id", nullable = false)
    private UUID senderAccountId;

    @Column(name = "receiving_account_id", nullable = true)
    private UUID receivingAccountId;

    @Column(name = "currency", nullable = false)
    private String currency;

    public Transaction() {
    }


    public Transaction(TransactionType type, double amount, LocalDateTime date, String description) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }


    public Transaction(TransactionType type, double amount, String currency,LocalDateTime date, String description, UUID account, UUID receivingAccountId) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.description = description;
        this.senderAccountId = account;
        this.receivingAccountId = receivingAccountId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(UUID account) {
        this.senderAccountId = account;
    }

    public UUID getReceivingAccountId() {
        return receivingAccountId;
    }

    public void setReceivingAccountId(UUID receivingAccountId) {
        this.receivingAccountId = receivingAccountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public TransactionDTO toDto() {
        return new TransactionDTO(
                amount,
                date.toString(),
                description,
                type.name()
        );
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                "currency='" + currency + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
