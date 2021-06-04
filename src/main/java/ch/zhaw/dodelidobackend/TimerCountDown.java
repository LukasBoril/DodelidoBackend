package ch.zhaw.dodelidobackend;

import org.springframework.stereotype.Component;

/**
 * Timer Class for the DoDeLiDo backend
 * Field variable "countDownTimeInSec" contains the starting value of the count down timer in seconds
 * Field variable "timerDone" contains a boolean that describes, if the time is already up
 *
 * @author Lukas Boril
 * @version 2021.05.29
 */

@Component
public class TimerCountDown {

    private int countDownTimeInSec;
    private boolean timerDone;

    /**
     * Constructor of the timer class
     * Initializes countDownTimeInSec with an int parameter
     * Initializes timerDone with 'false'
     */
    public TimerCountDown() {
        this.countDownTimeInSec = 10;
        this.timerDone = false;
    }

    /**
     * getter for the starting value of the count down timer in seconds
     * @return int describing the starting value of the count down timer in seconds
     */
    public int getCountDownTimeInSec() {
        return countDownTimeInSec;
    }

    /**
     * setter for the starting value of the count down timer in seconds
     * @param countDownTimeInSec int describing the starting value of the count down timer in seconds
     */
    public void setCountDownTimeInSec(int countDownTimeInSec) {
        this.countDownTimeInSec = countDownTimeInSec;
    }

    /**
     * getter for a boolean describing if the timer has already counted down to 0
     * @return boolean that describes if the time is up
     */
    public boolean isTimerDone() {
        return timerDone;
    }

    /**
     * setter of the boolean that describes if the time is up
     * @param timerDone new boolean value that describes if the time is up
     */
    public void setTimerDone(boolean timerDone) {
        this.timerDone = timerDone;
    }
}
