package bsplit;
/*
 * WindowDestroyer listens for a window-close event and then exits the program 
 */

import java.awt.event.*;

public class WindowDestroyer extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}