package ch.zhaw.dodelidobackend.controller;

import ch.zhaw.dodelidobackend.service.RoundCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for the roundCounter. The controller provides three end points
 * POST requests to "/roundCounter" to set the roundCounter back to 0
 * GET requests to "/roundCounter" to get the current value roundCounter as int
 * PUT requests to "/roundCounter"  to increment the roundCounter
 *
 * @author Lukas Boril
 * @version 2021.05.30
 */
@RestController
public class RoundCounterController {

    @Autowired
    private RoundCounterService roundCounterService;

    /**
     * This method can be reached by POST requests to "/roundCounter"
     * This method sets the current value of the roundCounter back to 1
     * This method shall be called after a player has given a wrong answer
     */
    @RequestMapping( method = RequestMethod.POST, value = "/roundCounter")
    public void setRoundCounterToOne() {
        roundCounterService.setRoundCounterToOne();
    }

    /**
     * This method can be reached by GET requests to "/roundCounter"
     * This method returns the current value of the roundCounter as int
     * @return current value of the roundCounter as int
     */
    @RequestMapping( method = RequestMethod.GET, value = "/roundCounter")
    public int getCurrentRoundCounterValue() {
        return roundCounterService.getCurrentRoundCounterValue();
    }

    /**
     * This method can be reached by PUT requests to "/roundCounter"
     * This method increments the roundCounter by one
     */
    @RequestMapping( method = RequestMethod.PUT, value = "/roundCounter")
    public void increaseCounterByOne() {
        roundCounterService.increaseCounterByOne();
    }

}
