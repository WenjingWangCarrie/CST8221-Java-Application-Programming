package calculator;

/**
 * File Name:       Calculator.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 2
 * Date:            November 27, 2017
 * Professor:       Svillen Ranev
 * Purpose:         This class is responsible for launching the application.
 *                  
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * This class is responsible for launching the application.
 *  
 * @author Wenjing Wang
 * @version 1
 * @see javax.swing.JFrame
 * @since 1.8.0_121
 */
public class Calculator {
	
	/**
	 * The main method to launch the frame.
	 * @param String[] args - command line arguments 
	 */
	public static void main(String[] args) {
		
		CalculatorSplashScreen obj = new CalculatorSplashScreen(5000); 
		CalculatorViewController controller = new CalculatorViewController();
		
		obj.showSplashWindow();
			
		/**
		 * The EventQueue.invokeLater method to invoke the frame.
		 * @param new Runnable - to run the frame 
		 */
		EventQueue.invokeLater(new Runnable() {
			
			/**
			 * The run method to run the frame.
			 */
			public void run() {
				JFrame frame = new JFrame();
				frame.setTitle("Calculator");
				frame.setMinimumSize(new Dimension(300, 460));  
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(controller);
				frame.setLocationByPlatform(true);  
				frame.setVisible(true);
			}
		});		
	}
}
