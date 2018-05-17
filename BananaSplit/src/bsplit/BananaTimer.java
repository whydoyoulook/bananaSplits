/*
 * THIS CLASS CALCULATES THE TIME AND RETURNS A DURATION IN MILLISECONDS
 */

package bsplit;

import java.time.LocalTime;
import java.time.Duration;

public class BananaTimer
{
    // create timer variables
    private LocalTime timeStart;
    private LocalTime currentTime;
    private Duration elapsedTime;
    private Duration previousTime;
    private timerStatus status;
  
    //class constructor.  Sets state to stopped, sets initial times
    //TODO get initial time information from saved file for existing run?
    public BananaTimer()
    {
        status = timerStatus.STOPPED;
        
        timeStart = LocalTime.MIN;
        currentTime = LocalTime.MIN;
        elapsedTime = Duration.ZERO;
        previousTime = Duration.ZERO;
    }
    
    //possible states that the timer can be set to
    private enum timerStatus
    {
        RUNNING, //the timer is active and counting time
        STOPPED, //the timer is stopped, when it starts again, it will start from zero
        PAUSED   //the timer is paused, when it starts again, it will continue from where it left off
    }

    // start running the timer from 0.00
    public void startTimer()
    {
        status = timerStatus.RUNNING;
        
        elapsedTime = Duration.ZERO;
        timeStart = LocalTime.now();
    }

    // set the timer back to 0 and set the timer starting point so getElapsedTime() shows 0 until timer is restarted
    public long resetTimer()
    {
        status = timerStatus.STOPPED;
        
        elapsedTime = Duration.ZERO;
        previousTime = Duration.ZERO;
        timeStart = LocalTime.MIN;

        return 0;
    }

    // stop the timer
    public void pauseTimer()
    {
        if (status == timerStatus.RUNNING) //only execute a pause if the timer is already running
        {
            status = timerStatus.PAUSED;
    
            previousTime = elapsedTime; //set the current time to the previous time
            elapsedTime = Duration.ZERO; //remove the current elapsed time
        }
        else {} //do nothing if the timer is already paused
    }
    
    //continue the timer from where it left off
    public void unpauseTimer()
    {
        status = timerStatus.RUNNING;
        
        timeStart = LocalTime.now();        
    }

    // provide the elapsed time
    public long getElapsedTime()
    {
        if (status == timerStatus.STOPPED) // if the timer hasn't started yet, display 0 ms.
        {
            return 0;
        }
        else // if the timer is running, return the elapsed time
        {
            currentTime = LocalTime.now();
            elapsedTime = Duration.between(timeStart, currentTime.plus(previousTime));
            
            return elapsedTime.toMillis(); // return the total time in Milliseconds
        }
    }
    
    //provide the timer status
    public String getTimerStatus()
    {
        String statusString = "";
        
        switch (status)
        {
            case RUNNING:
                statusString = "RUNNING";
                break;
            case STOPPED:
                statusString = "STOPPED";
                break;
            case PAUSED:
                statusString = "PAUSED";
                break;
        }
        
        return statusString;
    }
}