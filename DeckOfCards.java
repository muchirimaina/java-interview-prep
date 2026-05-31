import java.util.*;

// =====================
// ENUMS
// =====================

enum Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES
}

enum Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}

// =====================
// CARD
// =====================

class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

// =====================
// GENERIC DECK
// =====================

class Deck<T extends Card> {

    protected List<T> cards;

    public Deck(List<T> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public T dealCard() {

        if (cards.isEmpty()) {
            return null;
        }

        return cards.remove(cards.size() - 1);
    }

    public int remainingCards() {
        return cards.size();
    }
}

// =====================
// GENERIC HAND
// =====================

class Hand<T extends Card> {

    protected List<T> cards = new ArrayList<>();

    public void addCard(T card) {
        cards.add(card);
    }

    public List<T> getCards() {
        return cards;
    }
}

// =====================
// BLACKJACK CARD
// =====================

class BlackJackCard extends Card {

    public BlackJackCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    public int value() {

        switch (getRank()) {

            case JACK:
            case QUEEN:
            case KING:
                return 10;

            case ACE:
                return 11;

            case TWO:
                return 2;

            case THREE:
                return 3;

            case FOUR:
                return 4;

            case FIVE:
                return 5;

            case SIX:
                return 6;

            case SEVEN:
                return 7;

            case EIGHT:
                return 8;

            case NINE:
                return 9;

            case TEN:
                return 10;

            default:
                return 0;
        }
    }
}

// =====================
// BLACKJACK HAND
// =====================

class BlackJackHand extends Hand<BlackJackCard> {

    public int score() {

        int score = 0;
        int aces = 0;

        for (BlackJackCard card : cards) {

            score += card.value();

            if (card.getRank() == Rank.ACE) {
                aces++;
            }
        }

        // Convert Aces from 11 to 1 if bust
        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && score() == 21;
    }

    public boolean isBust() {
        return score() > 21;
    }
}

// =====================
// BLACKJACK DECK
// =====================

class BlackJackDeck extends Deck<BlackJackCard> {

    public BlackJackDeck() {
        super(createDeck());
    }

    private static List<BlackJackCard> createDeck() {

        List<BlackJackCard> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {

            for (Rank rank : Rank.values()) {

                cards.add(
                    new BlackJackCard(suit, rank)
                );
            }
        }

        return cards;
    }
}

// =====================
// DEMO
// =====================

public class Main {

    public static void main(String[] args) {

        BlackJackDeck deck = new BlackJackDeck();

        deck.shuffle();

        BlackJackHand hand = new BlackJackHand();

        hand.addCard(deck.dealCard());
        hand.addCard(deck.dealCard());

        System.out.println("Cards:");

        for (BlackJackCard card : hand.getCards()) {
            System.out.println(card);
        }

        System.out.println("Score: " + hand.score());
        System.out.println("Blackjack: " + hand.isBlackJack());
        System.out.println("Bust: " + hand.isBust());

        System.out.println("Remaining cards: "
                + deck.remainingCards());
    }
}