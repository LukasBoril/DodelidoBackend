package ch.zhaw.dodelidobackend.controller;

import ch.zhaw.dodelidobackend.model.Card;
import ch.zhaw.dodelidobackend.service.OpenCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  A rest interface class, that delivers a Array of three Card objects,
 *  with each new request one Card object is replaced randomly by a new one.
 *  The openCards Array of three Card objects (fixed size) represent the openly visible cards
 *  that will be displayed in the frontend to the user.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */

@RestController
public class OpenCardController {

    @Autowired
    private OpenCardService openCardService;

    /**
     * @return the openly visible cards as Card[] with 3 Card objects
     */
    @RequestMapping(method = RequestMethod.GET, value="/openCards")
    public Card[] getOpenCards() {
        return openCardService.getOpenCards();
    }

    /**
     * from the 3 Card objects int he array openCard, one should be replaced in every play move.
     * @return the openly visible cards as Card[] with one card replaced
     */
    @RequestMapping(method = RequestMethod.PUT, value="/openCards")
    public Card[] updateOpenCards() {
        openCardService.replaceOneOpenCard();
        return openCardService.getOpenCards();
    }
}
