package ch.zhaw.dodelidobackend.controller;

import ch.zhaw.dodelidobackend.service.TimerCountDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for the timer. The controller provides three end points
 * PUT requests to "/timer/setTo/{seconds}" where {seconds} is the new timer duration in sec (need to be between 1 and 100)
 * GET requests to "/timer/go" to start the timer
 * GET requests to "/timer/check" to check if the time is already up
 *
 * @author Lukas Boril
 * @version 2021.05.29
 */
@RestController
public class TimerCountDownController {

    @Autowired
    private TimerCountDownService timerCountDownService;

    /**
     * This method can be reached by PUT request to "/timer/setTo/{seconds}" where {seconds} is the new timer duration in sec (need to be between 1 and 100)
     * @param timerDurationInSec the int parameter for the new timer duration in sec (need to be between 1 and 100) is submitted in the URl after "/timer/setTo/"
     * @return returns 1 if the starting value has been successfully reset, otherwise 0
     */
    @RequestMapping( method = RequestMethod.PUT, value = "/timer/setTo/{seconds}")
    public int setNewTimerDuration(@PathVariable("seconds") int timerDurationInSec) {
        return timerCountDownService.setNewTimerDuration(timerDurationInSec);
    }

    /**
     * This method can be reached by GET requests to "/timer/go"
     * This method starts the timer.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/timer/go")
    public void startTimer() {
        timerCountDownService.startTimer();
    }

    /**
     * This method can be reached by GET requests to "/timer/check"
     * This method checks, if the started timer is already up
     * @return true if the timer has reached 0, false if the timer is still counting
     */
    @RequestMapping(method = RequestMethod.GET, value = "/timer/check")
    public boolean isTimerDone() {
        return timerCountDownService.isTimerDone();
    }


}
