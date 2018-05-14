package bsplit;
/*
 * This is the entry point of the BananaSplit speedrun timer.
 */

public class BananaSplit
{
    public static void main(String[] args)
    {
        //initialize the GUI
        BananaGUI splitsWindow = new BananaGUI();
        splitsWindow.setVisible(true);
        splitsWindow.setTimer(993600109); //test call for setter method in BananaGUI class;
        
        //test code below this point
        /*
        Timer timer = new Timer();
        timer.startTimer();
        
        try
        {
            Thread.sleep(88888); //minutes * seconds * ms (2 * 60 * 1000)
        } catch (InterruptedException e)
        {
            //Auto-generated catch block
            e.printStackTrace();
        }
        
        timer.getElapsedTime();*/
        
    }
}