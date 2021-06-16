package ch.zhaw.dodelidobackend;

import ch.zhaw.dodelidobackend.model.Card;
import ch.zhaw.dodelidobackend.service.OpenCardService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenCardServiceTest {

    private OpenCardService deck = new OpenCardService();

    @Test
    public void replaceCard() {
        Card[] round1 = deck.getOpenCards2();
        deck.replaceOneOpenCard2();
        Card[] round2 = deck.getOpenCards2();

        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if(round1[i].equals(round2[i])) {
                sum++;
            }
        }
        //one card is replaced, 2 card should stay the same. it can happen that
        // the replacement card is identical to its predecessor.
        assertTrue(sum >= 2);
    }


}
