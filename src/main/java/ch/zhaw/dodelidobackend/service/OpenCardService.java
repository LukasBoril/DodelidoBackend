package ch.zhaw.dodelidobackend.service;

/**
 * Service class of the Dodeldio cardstack. This class has a ArrayList<Card> instance variable called
 * openCards that holds 3 Cards and represent the openly visible cards.
 */

import ch.zhaw.dodelidobackend.model.CardStackService;
import ch.zhaw.dodelidobackend.model.Card;
import org.springframework.stereotype.Service;

@Service
public class OpenCardService {

    //@Autowired  hätte gerne singleton?
    private CardStackService cardStackService = new CardStackService();
    //private List<Card> openCards = new ArrayList<>();
    private Card[] openCards = new Card[3];


    public OpenCardService() {
        initOpenCards();
    }
/*
    public List<Card> getOpenCards() {
        return openCards;
    }  */

    public Card[] getOpenCards2() {
        return openCards;
    }

    public void replaceOneOpenCard() {  //unsure about return type (void or List)
        int random = (int) (Math.random() * 3);
        Card replaceCard = cardStackService.getRandomCardFromDeck();
        //openCards.add(random, replaceCard);

    }
    public void replaceOneOpenCard2() {  //unsure about return type (void or List)
        int random = (int) (Math.random() * 3);
        Card replaceCard = cardStackService.getRandomCardFromDeck();
        openCards[random] = replaceCard;

    }

    private void initOpenCards() {
       /*
        openCards.add(getRandomCardFromDeck());
        openCards.add(getRandomCardFromDeck());
        openCards.add(getRandomCardFromDeck());

        */

        openCards[0] = getRandomCardFromDeck();
        openCards[1] = getRandomCardFromDeck();
        openCards[2] = getRandomCardFromDeck();
    }

    private Card getRandomCardFromDeck() {
      return cardStackService.getRandomCardFromDeck();
    }


}
