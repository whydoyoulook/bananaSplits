package bsplit;

import java.time.LocalTime;
import java.time.Duration;

public class Timer
{
    //initialize timer variables
    private LocalTime timeStart;
    private LocalTime currentTime;
    private Duration elapsedTime;
    
    //start the timer
    public void startTimer()
    {
        timeStart = LocalTime.now();
    }
    
    public void updateTimer()
    {

    }
    
    //stop the timer
    public void stopTimer()
    {
        
    }
    
    //provide the elapsed time (remove VOID before completed)
    public long getElapsedTime()
    {
        currentTime = LocalTime.now();
        elapsedTime = Duration.between(timeStart, currentTime);
        
        return elapsedTime.toMillis();  //return the total time in Milliseconds
    }
}