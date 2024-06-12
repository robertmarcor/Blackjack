public class Card {
    private String cardName;
    private int cardValue;
    private String cardSuit;
    private String cardRank;

    public Card(String name, int value,String cardSuit, String cardRank){
        this.cardName = name;
        this.cardValue = value;
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }
    public String getCardName() {
        return cardName;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String getCardSuit(){
        return cardSuit;
    }

    public String getCardRank() {
        return cardRank;
    }
    @Override
    public String toString() {
        return cardName; // Return the card name as the string representation
    }
}
