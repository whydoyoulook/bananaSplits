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
        
        splitsWindow.setTimer(3, 21, 33, 34); //test call for setter method in BananaGUI class;
    }
}