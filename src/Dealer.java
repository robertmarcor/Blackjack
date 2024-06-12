import java.util.ArrayList;

public class Dealer {
    ArrayList<Card> cardOnHand;

    public Dealer(String cardGiven, int cardGivenValue, String cardSuit, String cardRank) {
        this.cardOnHand = new ArrayList<>();
        if (!cardGiven.isEmpty()) {
            this.cardOnHand.add(new Card(cardGiven, cardGivenValue, cardSuit, cardRank));
        }
    }

    public Card getCardOnHand(int index) {
        return cardOnHand.get(index);
    }
    public Card getFirstCard() {
        return cardOnHand.getFirst();
    }

    public void setCardOnHand(Card card) {
        cardOnHand.add(card);
    }
    public void getCardValue(int index){
        cardOnHand.get(index).getCardValue();
    }
    @Override
    public String toString() {
        for (int i = 0; i < cardOnHand.size(); i++){
            return cardOnHand.get(i).getCardName();
        }
        return null;
    }
}
