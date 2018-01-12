package calculator;

/**
 * File Name:       CalculatorViewController.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1
 * Date:           
 * Professor:       Svillen Ranev
 * Purpose:         The class CalculatorViewController is responsible
 *                  for building the calculator GUI.  
 * Class list:      CalculatorViewController, Controller
 *                  
 */

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

/**
 * This class is going to build the GUI for calculator. 
 * @author Wenjing Wang
 * @version 1
 * @see calculator
 * @since 1.8.0_121
 *
 */
public class CalculatorViewController extends JPanel {
	/** {@value} serial version number */
	private static final long serialVersionUID = 896163862031126857L;

	private JTextField display1;  /** the calculator display1 field reference */
	private JTextField display2; /** the calculator display2 field reference */
	private JLabel error;       /** the mode/error display label reference */
	private JButton dotButton; /** the decimal point (dot) button reference */

	/** {@value} nums array that store the numbers and operators */
	private static final String[] nums = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".",
			"\u00B1", "+" };

	/**
	 * This default constructor is used building the GUI of calculator.
	 * @param N/A
	 * @return N/A
	 */
	public CalculatorViewController() {
		/** The reference class for Controller to handle events */
		Controller controller = new Controller();
		/** to build the panel for the error label, textField, backspace button, chechBox and RadioButtons */
		JPanel north = new JPanel();    
		/** to build the panel for displaying the textField */
		JPanel display = new JPanel();   
		/** the backspace button */
		JButton backspace = new JButton();  
		/** the panel for the whole checkBox and radioButtons */
		JPanel modePanel = new JPanel();   
		/** set the chechBox and has text "Int" */
		JCheckBox checkBox = new JCheckBox("Int");  
		/** set the text of radioButton and is not selected */
		JRadioButton singleJRadioButton = new JRadioButton(".0", false); 
		/** .00 radioButton is selected by default at launch */
		JRadioButton doubleJRadioButton = new JRadioButton(".00", true); 
		/** set the text of Sci radioButton is not selected */
		JRadioButton sciJRadioButton = new JRadioButton("Sci", false);
		/** to build the panel for three radioButtons */
		JPanel radios = new JPanel();             
		/** to create a horizontal box to manage the checkBox and radioButtons */
		Box box = Box.createHorizontalBox();   
		/** to create a buttonGroup to group checkBox and radioButtons together */
		ButtonGroup group = new ButtonGroup(); 
		/** to create the main panel for whole keypad panel and operators */
		JPanel mainPanel = new JPanel();      
		/** to create the panel for displaying numbers and operators */
		JPanel keypad = new JPanel();   
		/** to build the panel for "C" and "=" buttons */
		JPanel operator = new JPanel();      
		
		/** to set the borderLayout for the whole panel */
		setLayout(new BorderLayout());  
		/** to set the black matte border for the whole panel */
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));  
		/** to define the layout for the north panel */
		north.setLayout(new BorderLayout()); 
		
		/** To set up the mode/error label */
		error = new JLabel("F");
		/** mode/error display label must have a specified dimension */
		error.setPreferredSize(new Dimension(35, 55));  
		/** set the button be transparent */
		error.setOpaque(true); 
		/** set the background color Yellow at launch */
		error.setBackground(Color.YELLOW);  
		/** have a black matte border with thickness 1 */
		error.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));  
		/** display the letter in the middle of operation horizontally */
		error.setHorizontalAlignment(JLabel.CENTER); 
		/** set the font and style for the error label */
		error.setFont(new Font(error.getFont().getName(), error.getFont().getStyle(), 20));

		/** To set up and display two textFields */
		display.setLayout(new GridLayout(2, 0));  
		display.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		display1 = new JTextField();
		/** textField has 16 columns and height of 30. */
		display1.setPreferredSize(new Dimension(16, 30)); 
		display1.setBackground(Color.WHITE); 
		display1.setEditable(false);  
		display1.setHorizontalAlignment(JTextField.RIGHT);  
		/** create an empty border that make up no space between two textFields */
		display1.setBorder(BorderFactory.createEmptyBorder()); 
		
		display2 = new JTextField();
		display2.setPreferredSize(new Dimension(16, 30)); 
		display2.setBackground(Color.WHITE);
		display2.setEditable(false);
		display2.setHorizontalAlignment(JTextField.RIGHT);
		/** display "0.0" at the default */
		display2.setText("0.0"); 
		display2.setBorder(BorderFactory.createEmptyBorder()); 

		/** To set up the backspace button */
		backspace.setPreferredSize(new Dimension(35, 55));
		backspace.setBackground(Color.YELLOW);
		backspace.setOpaque(true); 
		backspace.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		/** text Unicode for down left arrow */
		backspace.setText("\u21B2"); 
		backspace.setFont(new Font(backspace.getFont().getName(), Font.BOLD, 25));
		/** add the tool tip */
		backspace.setToolTipText("Alt-B(Backspace)"); 
		/** react with Alt-B key mnemonic */
		backspace.setMnemonic('b');   
		backspace.setActionCommand("\u21B2");
		backspace.addActionListener(controller);
		
		/** add display1 and display2 into the display panel */
		display.add(display1);  
		display.add(display2);  
		
		/** To add the components into north panel */
		north.add(error, BorderLayout.WEST);
		north.add(display, BorderLayout.CENTER);
		north.add(backspace, BorderLayout.EAST);

		/** boxPanel - set up the checkBox and radioButtons */
		modePanel.setLayout(new FlowLayout());
		modePanel.setBackground(Color.BLACK);
		modePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/** set the background color for chechBox and radioButtons */
		checkBox.setBackground(Color.GREEN);
		checkBox.addActionListener(controller);
		singleJRadioButton.setBackground(Color.YELLOW);
		singleJRadioButton.addActionListener(controller);
		doubleJRadioButton.setBackground(Color.YELLOW);
		doubleJRadioButton.addActionListener(controller);
		sciJRadioButton.setBackground(Color.YELLOW);
		sciJRadioButton.addActionListener(controller);

		/** add the radioButtons together */
		radios.setLayout(new GridLayout(1, 1, 5, 5));  
		radios.add(singleJRadioButton);
		radios.add(doubleJRadioButton);
		radios.add(sciJRadioButton);

		/** To set up the box and add the components into a whole box */
		box.setBackground(Color.BLACK);
		box.add(checkBox);
		/** to strut the align properly the components in the box container */
		box.add(Box.createHorizontalStrut(40)); 
		box.add(singleJRadioButton);
		box.add(doubleJRadioButton);
		box.add(sciJRadioButton);

		/** checkBox and the radioButtons included in a button group */
		group.add(checkBox);
		group.add(singleJRadioButton);
		group.add(doubleJRadioButton);
		group.add(sciJRadioButton);

		/** add box component into boxPanel */
		modePanel.add(box); 
		/** to add the boxPanel into north panel at the South position */
		north.add(modePanel, BorderLayout.SOUTH); 

		/** To set up the numeric keypad buttons */
		mainPanel.setLayout(new BorderLayout());  	
		/** set the layout for keypad buttons with same size and set the gaps */
		keypad.setLayout(new GridLayout(4, 3, 3, 3)); 
		/** set the border for the panel */
		keypad.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); 
		/** set the layout for "C" and "=" operator */
		operator.setLayout(new GridLayout(2, 1, 3, 3));  
		/** set the border between the keypad panel */
		operator.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2)); 

		/** a for loop to display the buttons into different panel */
		for (int i = 0; i < nums.length; i++) {
			if (nums[i].equals(".")) {
				dotButton = createButton(".", ".", Color.BLACK, Color.BLUE, new Controller());
				keypad.add(dotButton);
			} else if (nums[i].equals("\u00B1")) {
				keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.PINK, new Controller()));  
			} else if (nums[i].equals("+") || nums[i].equals("-") || nums[i].equals("*") || nums[i].equals("/")) {
				keypad.add(createButton(nums[i], nums[i], Color.BLACK, Color.CYAN, new Controller()));
			} else {
				keypad.add(createButton(nums[i], nums[i], Color.BLACK, Color.BLUE, new Controller()));
			}
		}

		operator.add(createButton("C", "C", Color.BLACK, Color.RED, new Controller())); 
		operator.add(createButton("=", "=", Color.BLACK, Color.MAGENTA, new Controller())); 
	
		/** add the north panel into the whole panel as North position */
		add(north, BorderLayout.NORTH);  
		/** add the keypad panel into the whole panel at the Center position */
		add(keypad, BorderLayout.CENTER); 
		/** add the keypad panel into the whole panel at the East position */
		add(operator, BorderLayout.EAST); 
	}

	/**
	 * The method is responsible for the creation of group of related buttons with the
	 * same basic properties.default constructor is used building the GUI of calculator.
	 * 
	 * @param String text - the button text label
	 * @param String ac - the action command string for that button
	 * @param Color fg - the foreground color of the button
	 * @param Color bg - the background color of the button
	 * @param ActionListener handler - the reference to instance of the event handler class
	 *        
	 * @return JButton buttons - the reference to the created button
	 */
	private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {
		/** to create a new button with a specified text label */
		JButton buttons = new JButton(text); 
	
		buttons.setForeground(fg);
		buttons.setBackground(bg);
		
		/** Set the action command for the button */
		if (ac != null) {
			buttons.setActionCommand(ac);
		}

		/** Set the properties of the button font */
		buttons.setFont(new Font(buttons.getFont().getName(), buttons.getFont().getStyle(), 20));
		/** Registers the handler as an Action event listener for the button */
		buttons.addActionListener(handler);

		/** Returns a reference to the created button */
		return buttons;
	}

	/**
	 * This class is a private inner class inside the CalculatorViewController class.
	 *  
	 * @author Wenjing Wang
	 * @version 1
	 * @see calculator
	 * @since 1.8.0_121
	 *
	 */
	private class Controller implements ActionListener {

		/**
		 * The method is responsible for the creation of group of related buttons with the
		 * same basic properties.default constructor is used building the GUI of calculator.
		 * 
		 * @param ActionEvent event - to display the textField text when it is get the action    
		 * @return N/A
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			display2.setText(event.getActionCommand());
		}
	}

}