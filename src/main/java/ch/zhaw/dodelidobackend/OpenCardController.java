package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  A rest interface class, that delivers a Array of three Cards,
 *  with each new request one Card is replaced randomly by a new one.
 *  @author Nadine Duss
 *  @version 2021.06.05
 */

@RestController
public class OpenCardController {

    @Autowired
    private OpenCardService openCardService;


    /**
     * from the 3 open cards, one should be replaced in every play move
     * @return the openly visible cards with one card replaced
     */
    @RequestMapping(method = RequestMethod.GET, value="/openCards")
    public Card[] addOpenCards() {
        openCardService.replaceOneOpenCard2();
        return openCardService.getOpenCards2();
    }



    /**
     *deleteeee
     */
    @RequestMapping(method = RequestMethod.PUT, value="/openCards")
    public Card[] replaceOpenCard2() {

        return openCardService.getOpenCards2();
    }

}
