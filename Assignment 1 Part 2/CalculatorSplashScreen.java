package calculator;

/**
 * File Name:       CalculatorSplashScreen.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 2
 * Date:            November 27, 2017
 * Professor:       Svillen Ranev
 * Purpose:         The class Controller is responsible for displaying
 *                  a splash screen before the launch of the application.       
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow; 

/**
 * This class is responsible for displaying the splash screen.
 *  
 * @author Wenjing Wang
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8.0_121
 */
public class CalculatorSplashScreen extends JWindow{
	/** {@value} Splash screen duration */
	private final int duration;

	/**
	 * This default constructor to set the show time of the splash screen.
	 * @param int duration - set the duration time of splash 
	 */
	public CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}
	
	/**
	 * The method is responsible for showing a splash screen in the center of
	 * the desktop for the amount of time given in the constructor. 
	 */
	public void showSplashWindow() {
		/** create content pane to hold components of splash screen */
		JPanel content = new JPanel(new BorderLayout()); 
		/** create panel to hold process component */
		JPanel panel = new JPanel(new BorderLayout());
		/** To set the progressBar to show the loading progress */
		JProgressBar progress = new JProgressBar();
		/** To display the content when progressing */
		JLabel progressLabel = new JLabel("Loading Calculator. Please wait...");
		
		content.setBackground(Color.RED);
		panel.setOpaque(false);
			
		progress.setMinimum(0);
		progress.setMaximum(duration);
		progress.setBackground(Color.WHITE);
		progress.setForeground(Color.BLUE);
		progress.setPreferredSize(new Dimension(10, 20));
		
		progressLabel.setForeground(Color.WHITE);
		progressLabel.setHorizontalAlignment(JLabel.CENTER);
		progressLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        progressLabel.setOpaque(false);
		
        /** To add processBar into panel */
        panel.add(progress, BorderLayout.NORTH);
        panel.add(progressLabel, BorderLayout.SOUTH);
        
		/** Set the window's bounds, position the window in the center of the screen */
		int width = 534 + 10;
		int height = 236 + 10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		/** set the location and the size of the window */
		setBounds(x, y, width, height); 
		
		/** Create the splash screen */
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("maple.gif")));
		JLabel demo = new JLabel("Wenjing Wang & 040812907", JLabel.CENTER);
		demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
		
		 /** To add component into the content panel */
	    content.add(panel, BorderLayout.NORTH);
		content.add(label, BorderLayout.CENTER);
		content.add(demo, BorderLayout.SOUTH);
		/** create custom RGB color */
	    Color color = new Color(44, 127, 211); 
	    content.setBorder(BorderFactory.createLineBorder(color, 10));
	   
	    setContentPane(content);	
	    /** To make the splash window visible */
		setVisible(true);  
		
		/** Wait a little while doing nothing, while the application is loading */
		try {
			for (int i = 0 ; i < duration; ++i) {
				progress.setValue(i);
				Thread.sleep(1);
			}
		} catch(InterruptedException e) {
			/** destroy the window and release all resources */
		}
		dispose();
	}
}
