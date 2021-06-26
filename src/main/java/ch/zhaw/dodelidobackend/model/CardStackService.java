package ch.zhaw.dodelidobackend.model;
/**
 * Service class of the Dodeldio cardstack. This class has a ArrayList<Card> instance variable for all
 * possible cards (25 in total, 5 different animals in 5 colours) called cardDeck.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */

import ch.zhaw.dodelidobackend.model.Card;

import java.util.ArrayList;
import java.util.List;

//@Service
//@Singleton
public class CardStackService {


    private List<Card> cardDeck = new ArrayList<>(); //

    /**
     * all 25 possible cards are added to the cardDeck in the parameterless constructor
     */
    public CardStackService() {
        for (int animalIndex = 0; animalIndex < 5; animalIndex++) {
            for (int colorindex = 0; colorindex < 5; colorindex++) {

                Card card = new Card(Card.Animals.values()[animalIndex], Card.Colors.values()[colorindex]);
                cardDeck.add(card);
            }
        }
    }


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
