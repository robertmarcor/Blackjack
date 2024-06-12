import java.util.ArrayList;

public class Player {
    ArrayList<Card> cardOnHand;

    public Player(String cardGiven, int cardGivenValue, String cardSuit, String cardRank){
        this.cardOnHand = new ArrayList<>();
        if (!cardGiven.isEmpty()) {
            this.cardOnHand.add(new Card(cardGiven, cardGivenValue, cardSuit, cardRank));
        }
    }
    public Card getCardOnHand(int index) {
        return cardOnHand.get(index);
    }
    public ArrayList<Card> getAllCards() {
        return cardOnHand;
    }

    public void setCardOnHand(Card card) {
        cardOnHand.add(card);
    }

    @Override
    public String toString() {
        for (int i = 0; i < cardOnHand.size(); i++){
            return cardOnHand.get(i).getCardName();
        }
        return null;
    }
}
