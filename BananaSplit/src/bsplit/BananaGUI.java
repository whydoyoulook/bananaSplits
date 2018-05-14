package bsplit;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BananaGUI extends JFrame //implements ActionListener
{

    //options for text box
    public static final int WIDTH = 247;  //width = height / 1.618 (golden ratio)
    public static final int HEIGHT = 400;
    
    /*
     * This section initializes all panels and subpanels that will be accessed
     * by getter and setter methods.
     */
    
    //create panels
    private JPanel gameInfoPanel = new JPanel(); //panel that shows game name, category, and attempt information
    private JPanel segmentInfoPanel = new JPanel(); //panel that shows the segment icons, names, and times
    private JPanel timerPanel = new JPanel(); //panel that shows the running timer
    private JPanel splitInfoPanel = new JPanel(); //panel that shows info on the previous split
    private JPanel recordInfoPanel = new JPanel(); //panel that shows Personal Best and World Record info
    
    //create subpanel labels
    private JLabel gameName = new JLabel();
    private JLabel gameCategory = new JLabel();
    private JLabel dailyAttempt = new JLabel();
    private JLabel completedAttempts = new JLabel();
    private JLabel totalAttempts = new JLabel();
    private JLabel elapsedTime = new JLabel();
    private JLabel sumOfBest = new JLabel();
    private JLabel worldRecord = new JLabel();

    public BananaGUI()
    {
        //setWindowInfo
        setSize(WIDTH,HEIGHT);
        addWindowListener(new WindowDestroyer()); //closes program
        setTitle("BananaSplits");
        
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        /*
         * This divides the JFrame into subsections and adds them to the main window
         */
        
        //create panel layouts
        createGameNamePanels(gameInfoPanel);
        createSegmentPanels(segmentInfoPanel);
        createTimerPanels(timerPanel);
        createSplitInfoPanels(splitInfoPanel);
        createRecordInfoPanels(recordInfoPanel);
                
        addPanel(gameInfoPanel, Color.GREEN, 40, 40);
        addPanel(segmentInfoPanel, Color.BLACK, 200, 9999); //segment panel should expand
        addPanel(timerPanel, Color.GREEN, 40, 40);
        addPanel(splitInfoPanel, Color.BLACK, 22, 22);
        addPanel(recordInfoPanel, Color.GREEN, 40, 40);
    }
    
    //create each panel and set the size
    private void addPanel(Container panel, Color panelColor, int minPanelHeight, int maxPanelHeight)
    {
        panel.setBackground(panelColor);
        panel.setMaximumSize(new Dimension(9999, maxPanelHeight));
        panel.setMinimumSize(new Dimension(WIDTH, minPanelHeight));
        panel.setPreferredSize(new Dimension(WIDTH, minPanelHeight)); //initial size should be minimum size
        ((JComponent) panel).setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.add(panel);
    }


    /*
     * This section contains the initial setup values for each individual panel.
     * Each panel section has its own individual layout with many subsections.
     */
    private void createGameNamePanels(Container gameInfoPanel)
    {
        //create layout and create constraints (for formatting where panels go)
        gameInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gameInfoConstraints = new GridBagConstraints();
        
        gameName.setText("Kirby's Dream Land");
        //gameInfoConstraints.fill = GridBagConstraints.HORIZONTAL;
        gameInfoConstraints.gridx = 0; //left subsection
        gameInfoConstraints.gridy = 0; //top subsection
        gameInfoConstraints.gridwidth = 3; //use all three columns
        gameInfoPanel.add(gameName, gameInfoConstraints);
        
        gameCategory.setText("any%");
        gameInfoConstraints.gridx = 0; //left subsection
        gameInfoConstraints.gridy = 1; //set below game name
        gameInfoConstraints.gridwidth = 3; //use all three columns
        gameInfoPanel.add(gameCategory, gameInfoConstraints);
        
        dailyAttempt.setText("0");
        gameInfoConstraints.gridx = 0; //far left subsection
        gameInfoConstraints.gridy = 2; //set below category
        gameInfoConstraints.gridwidth = 1; //use one column
        gameInfoConstraints.ipadx = 100;
        gameInfoConstraints.anchor = GridBagConstraints.LINE_START; //anchor the label to the left
        gameInfoPanel.add(dailyAttempt, gameInfoConstraints);
        
        completedAttempts.setText("15");
        gameInfoConstraints.gridx = 1; //middle subsection
        gameInfoConstraints.gridy = 2; //set below category
        gameInfoConstraints.anchor = GridBagConstraints.LINE_END; //anchor the label in the right
        gameInfoConstraints.ipadx = 10;
        gameInfoPanel.add(completedAttempts, gameInfoConstraints);
        
        totalAttempts.setText("33");
        gameInfoConstraints.gridx = 2; //far right subsection
        gameInfoConstraints.gridy = 2; //set below category

        gameInfoPanel.add(totalAttempts, gameInfoConstraints);
    }
    
    //create subpanels for the Segment section
    private void createSegmentPanels(Container segmentInfoPanel)
    {
    }

    //create subpanels for the Timer section
    private void createTimerPanels(Container timerPanel)
    {
        //create layout (for formatting where panels go)
        timerPanel.setLayout(new BorderLayout());
        elapsedTime.setText("0"); //initial text
        timerPanel.add(elapsedTime, BorderLayout.LINE_END);
    }

    //create subpanels for the Split Info section
    private void createSplitInfoPanels(Container splitInfoPanel)
    {
    }
    
    //create subpanels for the Record Info section    
    private void createRecordInfoPanels(Container recordInfoPanel)
    {
        //create layout and create constraints (for formatting where panels go)
        recordInfoPanel.setLayout(new GridBagLayout());
        GridBagConstraints recordConstraints = new GridBagConstraints();
        
        sumOfBest.setText("Sum of Best: xxx.xx.xxx");
        recordConstraints.gridx = 0;
        recordConstraints.gridy = 0;  //set on top
        recordInfoPanel.add(sumOfBest, recordConstraints);
        
        worldRecord.setText("World Record: xxx.xx.xxx, by Zzzzzzzz");
        recordConstraints.gridx = 0;
        recordConstraints.gridy = 1;  //set below sum of best
        recordInfoPanel.add(worldRecord, recordConstraints);
    }
    
    /*
     * These are the SETTER methods for changing values within sections
     */
    
    //set the display to the current timer value.  Input is in milliseconds
    public void setTimer(long timer)
    {
        String milliseconds, seconds, minutes, hours;
        
        if (timer > 3599999) //at least 1 hour in milliseconds
        {           
            //convert time to string and format to have leading zeroes
            milliseconds = String.format("%03d", timer%1000);
            seconds = String.format("%02d", (timer/1000)%60);
            minutes = String.format("%02d", ((timer/1000)/60)%60);
            hours = Long.toString(((timer/1000)/60)/60);  //no special formatting needed
            
            this.elapsedTime.setText(hours + ":" + minutes + ":" + seconds + ":" + milliseconds);                        
        }
        else if (timer > 59999) //less than 1 hour, but at least 1 minute
        {
            //convert time to string and format to have leading zeroes
            milliseconds = String.format("%03d", timer%1000);
            seconds = String.format("%02d", (timer/1000)%60);
            minutes = String.format("%02d", ((timer/1000)/60)%60);
            
            this.elapsedTime.setText(minutes + ":" + seconds + ":" + milliseconds);            
        }
        else if (timer > 999) //less than 1 minute, but at least 1 second
        {
            //convert time to string and format to have leading zeroes
            milliseconds = String.format("%03d", timer%1000);
            seconds = String.format("%02d", (timer/1000)%60);
            
            this.elapsedTime.setText(seconds + ":" + milliseconds);
        }
    }
}