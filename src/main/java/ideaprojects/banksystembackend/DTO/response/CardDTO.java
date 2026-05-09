package ideaprojects.banksystembackend.DTO.response;

import ideaprojects.banksystembackend.Entity.Card;
import ideaprojects.banksystembackend.Entity.CardType;

public class CardDTO {
    private String maskedNumber;
    private String cardType;
    private String expirationDate;
    private String maskedCVV;

    public CardDTO(String maskedNumber, String cardType, String expirationDate, String maskedCVV) {
        this.maskedNumber = maskedNumber;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.maskedCVV = maskedCVV;
    }

    public String getMaskedNumber() {
        return maskedNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getMaskedCVV() {
        return maskedCVV;
    }

    public Card toEntity(Card card) {
        return new Card(
                maskedNumber,
                CardType.valueOf(cardType),
                expirationDate,
                maskedCVV,
                card.getOwner()
        );
    }
}
