package calculator;

/**
 * File Name:       Calculator.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 1
 * Date:            
 * Professor:       Svillen Ranev
 * Purpose:         This class is responsible for launching the application.
 * Class list:      Calculator                 
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * This class is responsible for launching the application.
 *  
 * @author Wenjing Wang
 * @version 1
 * @see calculator
 * @since 1.8.0_121
 *
 */
public class Calculator {
	
	/**
	 * The main method to launch the frame.
	 * @param String[] args - command line arguments
	 * @return N/A
	 */
	public static void main(String[] args) {
		
		CalculatorSplashScreen obj = new CalculatorSplashScreen(5000);  
		CalculatorViewController controller = new CalculatorViewController();
		
		obj.showSplashWindow();
				
		/**
		 * The EventQueue.invokeLater method to invoke the frame.
		 * @param new Runnable - to run the frame
		 * @return N/A
		 */
		EventQueue.invokeLater(new Runnable() {
			
			/**
			 * The run method to run the frame.
			 * @param N/A
			 * @return N/A
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
