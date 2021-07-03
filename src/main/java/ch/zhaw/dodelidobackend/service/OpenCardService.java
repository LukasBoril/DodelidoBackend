package ch.zhaw.dodelidobackend.service;

/**
 * Service class of the Dodelido cardStack. This class has a ArrayList<Card> instance variable called
 * openCards that holds 3 Card objects (fixed size) and represent the openly visible cards.
 * It further contains the necessary methods to send those 3 Cards to the Frontend via the Controller as
 * well as replace one of the 3 Card objects with a new one at random.
 * Author: Nadine Duss
 * Version: 2021.07.01
 */

import ch.zhaw.dodelidobackend.model.CardStack;
import ch.zhaw.dodelidobackend.model.Card;
import org.springframework.stereotype.Service;

@Service
public class OpenCardService {

    private CardStack cardStack = new CardStack();
    //private List<Card> openCards = new ArrayList<>();
    private Card[] openCards = new Card[3];

    /**
     * The constructor initialises the Instance variable openCards (Card[]) with 3 random
     * card objects from the CardStack.
     */
    public OpenCardService() {
        initOpenCards();
    }

    /**
     * Getter methods for the Card array instance variable openCards.
     * @return the Card array openCards consisting of 3 Card objects
     */
    public Card[] getOpenCards() {
        return openCards;
    }

    /**
     * Replaces randomly one of the 3 Card objects in the  Card array openCards with
     * a random new Card from the cardStack.
     */
    public void replaceOneOpenCard() {
        int random = (int) (Math.random() * 3);
        Card replaceCard = getRandomCardFromDeck();
        openCards[random] = replaceCard;
    }

    private void initOpenCards() {
        openCards[0] = getRandomCardFromDeck();
        openCards[1] = getRandomCardFromDeck();
        openCards[2] = getRandomCardFromDeck();
    }

    private Card getRandomCardFromDeck() {
      return cardStack.getRandomCardFromDeck();
    }
}
