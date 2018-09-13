package Main;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimePanel extends JPanel {

	
	private int hour, min, second = 0;
    private JLabel time;

    private int tick = 0;

    public TimePanel() {
        setLayout(new GridBagLayout());

        //hour = new DigitPane();
        //min = new DigitPane();
        //second = new DigitPane();
        time = new JLabel();
        add(time);
        Font labelFont = time.getFont();
//        String labelText = time.getText();
//
//        int stringWidth = time.getFontMetrics(labelFont).stringWidth(labelText);
//        int componentWidth = time.getWidth();
//
//        // Find out how much the font can grow in width.
//        double widthRatio = (double)componentWidth / (double)stringWidth;
//
//        int newFontSize = (int)(labelFont.getSize() * widthRatio);
//        int componentHeight = time.getHeight();
//
//        // Pick a new font size so it will not be larger than the height of label.
//        int fontSizeToUse = Math.min(newFontSize, componentHeight);
//
//        // Set the label's font size to the newly determined size.
        time.setFont(new Font(labelFont.getName(), Font.PLAIN, 36));

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                hour = cal.get(Calendar.HOUR_OF_DAY);
                min = cal.get(Calendar.MINUTE);
                second = cal.get(Calendar.SECOND);
                if (second == 0 && min == 0) {
                	//SupremeBot.getInstance().refresh();
                }
                time.setText(getTimerText(hour, min, second));
                tick++;
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }
    
    private String getTimerText(int hour, int min, int sec) {
    	String biString = " A.M.";
    	if (hour > 11) {
    		biString = " P.M.";
    	}
    	hour = hour % 12;
    	if (hour == 0) {
    		hour = 12;
    	}
    	String hourString = "";
    	String minuteString = "";
    	String secString = "";
    	if (hour < 10) {
    		hourString = "0" + hour;
    	} else {
    		hourString = "" + hour;
    	}
    	if (min < 10) {
    		minuteString = "0" + min;
    	} else {
    		minuteString = "" + min;
    	}
    	if (sec < 10) {
    		secString = "0" + sec;
    	} else {
    		secString = "" + sec;
    	}
    	
    	
    	return hourString + ":" + minuteString + ":" + secString + biString;
    }
}
