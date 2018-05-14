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
    
    //end the timer
    public void endTimer()
    {
        
    }
    
    //provide the elapsed time (remove VOID before completed)
    public Long getElapsedTime()
    {
        currentTime = LocalTime.now();
        elapsedTime = Duration.between(timeStart, currentTime);
        
        Long milliseconds = elapsedTime.toMillis();
        
        System.out.println(milliseconds);
        return elapsedTime.toMillis();
    }
}