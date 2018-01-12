package calculator;

/**
 * File Name:       CalculatorSplashScreen.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 2
 * Date:            
 * Professor:       Svillen Ranev
 * Purpose:         The class Controller is responsible for displaying
 *                  a splash screen before the launch of the application.  
 * Class list:      CalculatorSplashScreen                
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
import javax.swing.JWindow; 

/**
 * This class is responsible for displaying the splash screen.
 *  
 * @author Wenjing Wang
 * @version 1
 * @see calculator
 * @since 1.8.0_121
 *
 */
public class CalculatorSplashScreen extends JWindow{
	/** {@value} Splash screen duration time */
	private final int duration;

	/**
	 * This default constructor to set the show time of the splash screen.
	 * @param int duration - set the duration time of splash
	 * @return N/A
	 */
	public CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}
	
	/**
	 * The method is responsible for showing a splash screen in the center of
	 * the desktop for the amount of time given in the constructor. 
	 * @param N/A 
	 * @return N/A
	 */
	public void showSplashWindow() {
		/** To create a main content pane */
		JPanel content = new JPanel(new BorderLayout()); 
		
		/** Set the window's bounds, position the window in the center of the screen */
		int width = 534 + 10;
		int height = 236 + 10;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		
		/** To create the splash screen */
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("maple.gif")));
		JLabel demo = new JLabel("Wenjing Wang & 040812907", JLabel.CENTER);
		/** create custom RGB color */
		Color color = new Color(44, 127, 211); 
		
		/** set the location and the size of the window */
		setBounds(x, y, width, height); 
			
		content.setBackground(Color.RED);
		demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
		content.add(label, BorderLayout.CENTER);
		content.add(demo, BorderLayout.SOUTH);
	    content.setBorder(BorderFactory.createLineBorder(color, 10));
		
	    setContentPane(content);	
	    /** To make the splash window visible */
		setVisible(true);  
		
		/** Wait a little while doing nothing, while the application is loading */
		try {
			Thread.sleep(duration);
		} catch(InterruptedException e) {
			/** destroy the window and release all resources */
		}
		dispose();
	}
}
