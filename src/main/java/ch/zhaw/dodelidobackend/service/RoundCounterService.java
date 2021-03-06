package ch.zhaw.dodelidobackend.service;

import ch.zhaw.dodelidobackend.model.RoundCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ServiceClass for the roundCounter
 * This class provides the methods to interact with the roundCounter
 * This class contains the logic for the roundCounter interaction
 *
 * @author Lukas Boril
 * @version 2021.05.30
 */

@Service
public class RoundCounterService {

    // @Autowired
    static RoundCounter roundCounter = new RoundCounter();


    /**
     * Method to set the roundCounter back to onw
     */
    public static void setRoundCounterToOne() {
        roundCounter.setRoundsSinceLastFail(1);
    }

    /**
     * Method to get the current value of the roundCounter
     * @return int current value of the roundCounter
     */
    public static int getCurrentRoundCounterValue() {
        return roundCounter.getRoundsSinceLastFail();
    }

    /**
     * Method to increase the roundCounter by one
     */
    public void increaseCounterByOne() {
        int current = roundCounter.getRoundsSinceLastFail();
        current++;
        roundCounter.setRoundsSinceLastFail(current);
    }

}
