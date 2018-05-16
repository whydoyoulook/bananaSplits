/*
 * THIS CLASS GRACEFULLY EXITS THE PROGRAM WHEN THE WINDOW IS CLOSED
 */

package bsplit;

import java.awt.event.*;

//WindowDestroyer listens for a window-close event and then exits the program 
public class WindowDestroyer extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}