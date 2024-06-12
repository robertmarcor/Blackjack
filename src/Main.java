import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Card> deckOfCards;
    static Wallet wallet;
    static Random random = new Random();
    static Player player;
    static Dealer dealer;
    static int playerScore = 0;
    static int dealerScore = 0;

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        deckOfCards = cardDeck.deckOfCards;
        System.out.println("\n" +
                "██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗\n" +
                "██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝\n" +
                "██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ \n" +
                "██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ \n" +
                "██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗\n" +
                "╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝\n");
        sleep(1000);
        System.out.println("Here are the basic rules of the game:\n" +
                "\n" +
                "Objective: The primary objective of Blackjack is to beat the dealer's hand without exceeding a total of 21.\n" +
                "Card Values:\n" +
                "Cards 2 through 10 are worth their face value.\n" +
                "Face cards (Jack, Queen, King) are each worth 10 points.\n" +
                "Aces can be worth either 1 point or 11 points, depending on which value benefits the player the most without exceeding 21.");
        // sleep(5000);
/*        System.out.print("Would you like to start?\n Type 'yes' or 'no' : ");
        String input = scanner.nextLine().toLowerCase();
        String[] acceptableInputs  = {"yes", "y", "ye", "ys"};
        boolean isAcceptableInput = false;
        for (String acceptableInput : acceptableInputs ) {
            if (input.equals(acceptableInput)) {
                isAcceptableInput = true;
                break;
            }
        }
        if(isAcceptableInput){
            System.out.println("Starting Game....");
            sleep(2500);
            startGame();
        }
        else {
            System.out.println("Goodbye");
            System.exit(0);
        }*/
        startGame();
    }
static int startMoney = 50000;
    static int betAmount;
    public static void startGame(){
        wallet = new Wallet(startMoney, 0, 0);
        clearScreen();
        System.out.println("-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=-=x=");
        betAmount = 0; // Default value for the bet amount
        while (true) {
            showWallet();
            System.out.print("Would you like to bet? Leaving blank will result in free game\n" +
                    "Enter bet amount: ");
            String input = scanner.nextLine();

            // Check if the input is not empty
            if (!input.isEmpty()) {
                try {
                    betAmount = Integer.parseInt(input);
                    if (betAmount > startMoney){
                        System.out.println("Invalid bet amount");
                        System.out.println("Bet is larger than available amount. Available amount=" + startMoney);
                    }
                    System.out.println(ANSI_GREEN + "You are betting " + betAmount + "$" + ANSI_RESET);
                    startMoney -= betAmount;
                    wallet.setMoney(startMoney, -betAmount);
                    sleep(2000);
                    clearScreen();
                    showWallet();
                    break; // Exit the loop if the input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid bet amount.");
                }
            } else {
                System.out.println("You will play a free game");
                break; // Exit the loop if the input is empty
            }
        }

        System.out.println("Cards are being dealt");
        sleep(1000);
        dealerPull();
        sleep(1500);
        pullCard();
        System.out.print("[Hit] or [Stand] ?\nAction: ");
        boolean moveOn = false;
        while (!moveOn) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("hit")) {
                System.out.println("Player Hits");
                moveOn = hitCard(moveOn); // Update moveOn based on the return value of hitCard
            } else if (input.equals("stand")) {
                System.out.println("Player Stands");
                stand();
                moveOn = true;
            } else {
                System.out.println("Typo, check spelling!");
            }
        }

    }

    public static void pullCard() {
        player = new Player("", 0,"",""); // Start with empty hand
        System.out.println(ANSI_CYAN + "The Player pulls " + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            int x = random.nextInt(deckOfCards.size());
            Card card = deckOfCards.get(x);
            player.setCardOnHand(card);
            playerScore += card.getCardValue();
            if (i == 1) {
                System.out.print(card.getCardName());
            }
            else{
                System.out.print(card.getCardName() + " & ");
            }
        }
        sleep(2500);
        showPlayerHand();
    }
    public static void dealerPull(){
        dealer = new Dealer("",0, "", "");
        dealerScore = 0;
        int secondCardValue = 0;
        System.out.println(ANSI_YELLOW + "The dealer pulls" + ANSI_RESET);
        for (int i = 0; i < 2; i++) {
            Card card = drawRandomCard();
            dealer.setCardOnHand(card);
            dealerScore += card.getCardValue();
            if (i == 1) {
                secondCardValue = card.getCardValue();
                System.out.print(" X of ?");
            }
            else{
            System.out.print(card.getCardName() + ",");
            }
        }
        System.out.println("\n---------------------------");
        System.out.println("Dealer's Hand: ");
        printCardsHorizontally(dealer.cardOnHand,true);
        System.out.println("Dealer has: " + (dealerScore - secondCardValue));
        System.out.println("----------------------------");
    }

    public static boolean hitCard(boolean moveOn){
        Card card = drawRandomCard();
        player.setCardOnHand(card);
        printSingleCard(card);
        playerScore += card.getCardValue();
        showPlayerHand();
        System.out.println("Hit again or Stand?");
        if (playerScore == 21){
            System.out.println("BLACKJACK!");
            return true;
        }
        else if (playerScore > 21){
            System.out.println("BUST! You Lost");
            return true;
        }
        return false;
    }
    public static void stand(){
        dealerReveal();
        System.out.println("Dealer starts drawing");
        sleep(1000);
        while(dealerScore <= 21){
            Card card = drawRandomCard();
            dealer.setCardOnHand(card);
            dealerScore += card.getCardValue();
            dealerReveal();
            sleep(2500);
            if (dealerScore == playerScore){
                System.out.println("It´s a draw");
                System.out.println("You wont lose any money");
                showWallet();
                break;
            }
            else if (dealerScore > 21){
                System.out.println("You Win! +" + betAmount*2 + "$");
                wallet.setMoney(startMoney, betAmount *2);
                wallet.setGain(1);
                showWallet();
                break;
            } else if (dealerScore > playerScore){
                System.out.println("You Lose! -" + betAmount + "$");
                showWallet();wallet.setMoney(startMoney, betAmount *-2);
                wallet.setLost(1);
                showWallet();
                break;
            }
        }

    }
    public static Card drawRandomCard() {
        int x = random.nextInt(deckOfCards.size());
        Card card = deckOfCards.get(x);
        deckOfCards.remove(x); // Remove the drawn card from the deck
        return card;
    }

    public static void dealerReveal(){
        System.out.println("---------------------------");
        System.out.println("Dealer's Hand: ");
        printCardsHorizontally(dealer.cardOnHand,false);
        System.out.println("Dealer has: " + (dealerScore));
        System.out.println("----------------------------");
    }
    public static void showPlayerHand(){
        System.out.println("\n---------------------------");
        System.out.println("Total hand value: " + playerScore);
        System.out.println("Player's Hand: ");
        printCardsHorizontally(player.getAllCards(), false);
        System.out.println("----------------------------");
    }

    public static void showWallet(){
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("      "+ ANSI_GREEN + "Wallet: " + wallet.getMoney() + "$" + ANSI_RESET + "      |         Won: " + wallet.getGain() + " Lost: " + wallet.getLost() + "           ");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void printCardBack(ArrayList<Card> cards){
        StringBuilder[] rows = new StringBuilder[5];

        // Initialize StringBuilder objects for each row
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }

        // Build the strings for each row
        for (Card card : cards) {

            rows[0].append("┌─────┐ ");
            rows[1].append("|░░░░░| ");
            rows[2].append("|░░░░░| ");
            rows[3].append("|░░░░░| ");
            rows[4].append("└─────┘ ");
        }

        // Print each row
        for (StringBuilder row : rows) {
            System.out.println(row);
        }
    }

    public static void printSingleCard(Card card){
        StringBuilder[] rows = new StringBuilder[5];

        // Initialize StringBuilder objects for each row
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
            String rank = card.getCardRank();
            String suit = card.getCardSuit();

            // Top Frame
            rows[0].append("┌─────┐ ");

            // Card rank
            if (rank.length() == 1) {
                rows[1].append("|").append(rank).append("    | ");
            } else {
                rows[1].append("|").append(rank).append("   | ");
            }

            // Card suit
            rows[2].append("|  ").append(suit).append("  | ");

            // Card rank
            if (rank.length() == 1) {
                rows[3].append("|    ").append(rank).append("| ");
            } else {
                rows[3].append("|   ").append(rank).append("| ");
            }
            // Bottom frame
            rows[4].append("└─────┘ ");

        // Print each row
        for (StringBuilder row : rows) {
            System.out.println(row);
        }
    }
    public static void printCardsHorizontally(ArrayList<Card> cards, boolean dealer) {
        StringBuilder[] rows = new StringBuilder[5];

        // Initialize StringBuilder objects for each row
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }

        // Flag to track if it's the first card
        boolean isFirstCard = true;

        // Build the strings for each row
        for (Card card : cards) {
            String rank = card.getCardRank();
            String suit = card.getCardSuit();

            // Top Frame
            rows[0].append("┌─────┐ ");

            // Card rank
            if (isFirstCard || !dealer) {
                if (rank.length() == 1) {
                    rows[1].append("|").append(rank).append("    | ");
                } else {
                    rows[1].append("|").append(rank).append("   | ");
                }
            } else {
                rows[1].append("|   ? | ");
            }

            // Card suit
            rows[2].append("|  ").append(suit).append("  | ");

            // Card rank
            if (isFirstCard || !dealer) {
                if (rank.length() == 1) {
                    rows[3].append("|    ").append(rank).append("| ");
                } else {
                    rows[3].append("|   ").append(rank).append("| ");
                }
            } else {
                rows[3].append("| ?   | ");
            }

            // Bottom frame
            rows[4].append("└─────┘ ");

            // If it's the first card, set the flag to false for subsequent cards
            isFirstCard = false;
        }

        // Print each row
        for (StringBuilder row : rows) {
            System.out.println(row);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
