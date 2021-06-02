package ch.zhaw.dodelidobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ServiceClass for the timer
 * This class provides the methods to interact with the timer
 * THis class contains the logic for the timer interaction
 *
 * @author Lukas Boril
 * @version 2021.05.29
 */
@Service
public class TimerCountDownService {

    private TimerCountDown timerCountDown = new TimerCountDown(10);

    /**
     * Method to reset the starting value (in seconds) of the count down timer
     * Default is 10 seconds. The update of the starting value is only performed if a valid input is provided
     * @param timerDurationInSec int to set the new starting value (in seconds) of the count down timer. Valid are values from 1 to 100
     * @return returns 1 if the starting value has been successfully reset, otherwise 0
     */
    public int setNewTimerDuration(int timerDurationInSec) {
        if ((0<timerDurationInSec)&&(timerDurationInSec<=100)) {
            timerCountDown.setCountDownTimeInSec(timerDurationInSec);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Method to start the timer. Timer counts down to 0 from the set sarting value in seconds.
     * Once 0 is reached, the boolean isDOne of the timer is set to true
     */
    public void startTimer() {
        timerCountDown.setTimerDone(false);
        final int[] currentTime = {timerCountDown.getCountDownTimeInSec()};
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            public void run() {
                // System.out.println(currentTime[0]);
                currentTime[0]--;
                if (currentTime[0] ==0) {
                    timer.cancel();
                    timerCountDown.setTimerDone(true);
                }
            }
        };
        timer.scheduleAtFixedRate(t, 1000, 1000 );
    }

    /**
     * Method to ask the timer if he already finished counting to 0
     * @return true if the timer has reached 0, false if the timer is still counting
     */
    public boolean isTimerDone() {
        return timerCountDown.isTimerDone();
    }


}
