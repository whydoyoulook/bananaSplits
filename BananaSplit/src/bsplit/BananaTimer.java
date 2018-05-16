/*
 * THIS CLASS CALCULATES THE TIME AND RETURNS A DURATION IN MILLISECONDS
 */

package bsplit;

import java.time.LocalTime;
import java.time.Duration;

public class BananaTimer
{
    //initialize timer variables and set all to 0
    private LocalTime timeStart = LocalTime.MIN;
    private LocalTime currentTime = LocalTime.MIN;;
    private Duration elapsedTime = Duration.ZERO;
    
    //start the timer
    public void startTimer()
    {
        timeStart = LocalTime.now();
    }
    
    //set the timer back to 0 and set the timer starting point so getElapsedTime() shows 0 until timer is restarted 
    public long resetTimer()
    {
        elapsedTime = Duration.ZERO;
        timeStart = LocalTime.MIN;
        
        return 0;
    }
    
    //stop the timer
    public void stopTimer()
    {
        
    }
    
    //provide the elapsed time (remove VOID before completed)
    public long getElapsedTime()
    {
        if (timeStart.equals(LocalTime.MIN)) //if the timer hasn't started yet, display 0 ms.
        {
            return 0;
        }
        else
        {
            currentTime = LocalTime.now();
            elapsedTime = Duration.between(timeStart, currentTime);
            return elapsedTime.toMillis();  //return the total time in Milliseconds
        }
    }
}