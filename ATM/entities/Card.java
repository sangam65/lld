package ATM.entities;

public class Card {
    private final String cardId;
    private final String pin;
    public String getCardId() {
        return cardId;
    }
    public String getPin() {
        return pin;
    }
    public Card(String cardId, String pin) {
        this.cardId = cardId;
        this.pin = pin;
    }

    

}
