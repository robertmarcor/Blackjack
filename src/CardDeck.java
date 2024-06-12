import java.util.ArrayList;

public class CardDeck {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    ArrayList<Card> deckOfCards = new ArrayList<>();
    String[] suits = {
            ANSI_PURPLE + "♠" + ANSI_RESET,
            ANSI_RED + "♥" + ANSI_RESET,
            ANSI_RED + "♦" + ANSI_RESET,
            ANSI_PURPLE + "♣" + ANSI_RESET};

    String[] suits2 = {"club", ANSI_RED + "diamond", ANSI_RED + "heart", ANSI_BLACK + "spade"};
    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    int[] rankValue = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1}; // Cards Values from 2-10, court cards=10 and Ace=1

    public CardDeck() {
        createDeck();
/*        for (Card card : deckOfCards){
            System.out.println(card.getCardName() + " : " + card.getCardValue());
        }*/
    }

    public void createDeck() {
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++){
                String cardName = ranks[i] + " of " + suit;
                int cardValue = rankValue[i];
                Card card = new Card(cardName, cardValue, suit, ranks[i]);
                deckOfCards.add(card);
            }
        }
    }

/*    public void printDeckOfCards(){
        for (String suit : suits) {
            System.out.println("---- " + suit.toUpperCase() + "S ----");
            for (String card : deckOfCards.keySet()) {
                if (card.contains(suit)) {
                    int cardValue = deckOfCards.get(card);
                    String color = (suit.equals("club") || suit.equals("spade")) ? ANSI_BLACK : ANSI_RED;
                    System.out.println(color + card + " : " + cardValue + ANSI_RESET);
                }
            }
            System.out.println(); // Add an empty line between suits
        }
    }*/
}
