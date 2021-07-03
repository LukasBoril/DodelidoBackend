package ch.zhaw.dodelidobackend.model;
/**
 * This class holds an ArrayList<Card> instance variable for all
 * possible cards (25 in total, 5 different animals in 5 colours) of the game called cardDeck.
 * This class provides methods to randomly return one of the 25 cards.
 *  @author Nadine Duss
 *  @version 2021.07.03
 */

import java.util.ArrayList;
import java.util.List;

public class CardStack {
    private final List<Card> cardDeck = new ArrayList<>(); //

    /**
     * All 25 possible cards are added to the cardDeck in the parameterless constructor.
     */
    public CardStack() {
        for (int animalIndex = 0; animalIndex < 5; animalIndex++) {
            for (int colorindex = 0; colorindex < 5; colorindex++) {

                Card card = new Card(Card.Animals.values()[animalIndex], Card.Colors.values()[colorindex]);
                cardDeck.add(card);
            }
        }
    }

    /**
     * Getter method for the cardDeck instance variable of the CardStack class.
     * @return the complete cardDeck as ArrayList<Card> with all 25 possible cards
     */
    public List<Card> getCardDeck() {
        return cardDeck;
    }

    /**
     * one card is drawn randomly from the cardDeck.
     * @return a new Card drawn at random
     */
    public Card getRandomCardFromDeck() {
        int random = (int) (Math.random() * cardDeck.size());
        return cardDeck.get(random);
    }
}
