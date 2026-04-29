package ideaprojects.banksystembackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;
    @Column(name = "card_expect_date", nullable = false, length = 5)
    private String cardExpectDate;

    @Column(name = "card_cvv", nullable = false, length = 3)
    private String cardCvv;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    public Card() {
    }

    public Card(String cardNumber, CardType cardType, String cardExpectDate, String cardCvv, User owner) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardExpectDate = cardExpectDate;
        this.cardCvv = cardCvv;
        this.owner = owner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getCardExpectDate() {
        return cardExpectDate;
    }

    public void setCardExpectDate(String cardExpectDate) {
        this.cardExpectDate = cardExpectDate;
    }

    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber.substring(12, 16) + '\'' +
                "cardType=" + cardType +
                ", cardExpectDate='" + cardExpectDate + '\'' +
                ", owner=" + owner +
                '}';
    }
}
