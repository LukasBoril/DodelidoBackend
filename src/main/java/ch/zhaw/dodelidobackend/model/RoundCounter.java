package ch.zhaw.dodelidobackend.model;

import org.springframework.stereotype.Component;

/**
 * RoundCounter Class for the DoDeLiDo backend
 * Field variable "roundsSinceLastFail" contains an int that counts the round since the last wrong answer
 * Everytime a wrong answer is given, this field variable is set to 0 again
 *
 * @author Lukas Boril
 * @version 2021.05.30
 */
@Component
public class RoundCounter {

    private int roundsSinceLastFail;

    /**
     * Constructor for the RoundCounter. Initializes roundsSinceLastFail with 1
     */
    public RoundCounter() {
        this.roundsSinceLastFail = 1;
    }

    /**
     * setter for roundsSinceLastFail. This method is used to set the counter back to 0 after a wrong answer
     * @param roundsSinceLastFail int value giving the new int value for the roundCounter.
     */
    public void setRoundsSinceLastFail(int roundsSinceLastFail) {
        this.roundsSinceLastFail = roundsSinceLastFail;
    }


    /**
     * getter for roundsSinceLastFail
     * @return int value giving the amounts of played rounds since the last wrong answer
     */
    public int getRoundsSinceLastFail() {
        return roundsSinceLastFail;
    }
}
