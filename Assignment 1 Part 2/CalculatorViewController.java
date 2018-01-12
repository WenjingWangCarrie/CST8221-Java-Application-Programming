package calculator;

/**
 * File Name:       CalculatorViewController.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      1, Part 2
 * Date:            November 27, 2017
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
import javax.swing.KeyStroke;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent; 

/**
 * This class is going to build the GUI for calculator. 
 * @author Wenjing Wang
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8.0_121
 *
 */
public class CalculatorViewController extends JPanel {
	/** the calculator display1 field reference */
	private JTextField display1;  
	/** the calculator display2 field reference */
	private JTextField display2; 
	/** the mode/error display label reference */
	private JLabel error; 
	/** the decimal point (dot) button reference */
	private JButton dotButton; 
	/** the radio button reference to help working on clicking multiple times checkBox */
    private JRadioButton doubleJRadioButton; 
	
	/** {@value} nums array that store the numbers and operators */
	private static final String[] nums = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".",
			"\u00B1", "+" };
	
	/** {@value} KeyEvent constant to represent the numbers in the keyboard */
	private static final int keyEvents[] = new int[] {KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_SLASH,
			KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_COMMA, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3,
			KeyEvent.VK_MINUS, KeyEvent.VK_0, KeyEvent.VK_PERIOD, KeyEvent.VK_P, KeyEvent.VK_EQUALS};

	/**
	 * This default constructor is used building the GUI of calculator. 
	 */
	public CalculatorViewController() {
		/** Class instances to handle buttons calculations */
		CalculatorModel model = new CalculatorModel(); 
		/** The reference class for Controller to handle events */
		Controller controller = new Controller(model);
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
		/** the button to handle "C" */
		JButton clear = new JButton("C");
		/** the button to handle "=" */
		JButton equal = new JButton("=");
		       
		/** to set the border and layout for the whole panel */
		setLayout(new BorderLayout());  
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK)); 
		/** define the layout for the north panel */
		north.setLayout(new BorderLayout()); 
		
		/** To set up the mode/error label */
		error = new JLabel("F"); 
		error.setPreferredSize(new Dimension(35, 55)); 
		error.setOpaque(true);  
		error.setBackground(Color.YELLOW);   
		error.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));   
		error.setHorizontalAlignment(JLabel.CENTER);  
		error.setFont(new Font(error.getFont().getName(), error.getFont().getStyle(), 20));

		/** To set up and display two textFields */
		display.setLayout(new GridLayout(2, 0));  
		display.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		display1 = new JTextField(); 
		display1.setPreferredSize(new Dimension(16, 30)); 
		display1.setColumns(16);
		display1.setBackground(Color.WHITE); 
		display1.setEditable(false);  
		display1.setHorizontalAlignment(JTextField.RIGHT);   
		display1.setBorder(BorderFactory.createEmptyBorder()); 
		display2 = new JTextField();
		display2.setPreferredSize(new Dimension(16, 30)); 
		display2.setColumns(16);
		display2.setBackground(Color.WHITE);
		display2.setEditable(false);
		display2.setHorizontalAlignment(JTextField.RIGHT); 
		display2.setText("0.0");  
		display2.setBorder(BorderFactory.createEmptyBorder());  
		
		/** To set up the backspace button */
		backspace.setPreferredSize(new Dimension(35, 55));
		backspace.setBackground(Color.YELLOW); 
		/** set the button transparent */
		backspace.setContentAreaFilled(true);
		backspace.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK)); 
		backspace.setText("\u21B2");  
		backspace.setFont(new Font(backspace.getFont().getName(), Font.BOLD, 25)); 
		backspace.setToolTipText("Alt-B(Backspace)");   
		backspace.setMnemonic('b');  
		backspace.setActionCommand("\u21B2");
		backspace.addActionListener(controller);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put("\u21B2", new KeyActions(backspace));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "\u21B2");
			
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
		
		/** To set the background color for chechBox and radioButtons */	
		checkBox.setBackground(Color.GREEN);
		checkBox.addActionListener(controller);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put("Int", new KeyActions(checkBox));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "Int");
		
		singleJRadioButton.setBackground(Color.YELLOW);
		singleJRadioButton.addActionListener(controller);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put(".0", new KeyActions(singleJRadioButton));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), ".0");
				
		/** set the text of doubleJRadioButton and is selected in default */
		doubleJRadioButton = new JRadioButton(".00", true); 
		doubleJRadioButton.setBackground(Color.YELLOW);
		doubleJRadioButton.addActionListener(controller);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put(".00", new KeyActions(doubleJRadioButton));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), ".00");
				
		sciJRadioButton.setBackground(Color.YELLOW);
		sciJRadioButton.addActionListener(controller);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put("Sci", new KeyActions(sciJRadioButton));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "Sci");

		/** add the radioButtons together */
		radios.setLayout(new GridLayout(1, 1, 5, 5)); // to set the radioButtons in same size
		radios.add(singleJRadioButton);
		radios.add(doubleJRadioButton);
		radios.add(sciJRadioButton);

		/** To set up the box and add the components into a whole box */
		box.setBackground(Color.BLACK);
		box.add(checkBox); 
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
		north.add(modePanel, BorderLayout.SOUTH); 
		
		/** To set up the numeric keypad buttons */
		mainPanel.setLayout(new BorderLayout());  	 
		keypad.setLayout(new GridLayout(4, 3, 3, 3));  
		keypad.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));  
		operator.setLayout(new GridLayout(2, 1, 3, 3));   
		operator.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2)); 

		/** a for loop to display the buttons for different situation */
		for (int i = 0; i < nums.length; i++) {
			if (nums[i].equals(".")) {
				dotButton = createButton(".", ".", Color.BLACK, Color.BLUE, controller);
				keypad.add(dotButton);
			} else if (nums[i].equals("\u00B1")) {
				keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.PINK, controller));  
			} else if (nums[i].equals("+") || nums[i].equals("-") || nums[i].equals("*") || nums[i].equals("/")) {
				keypad.add(createButton(nums[i], nums[i], Color.BLACK, Color.CYAN, controller));
			} else {
				keypad.add(createButton(nums[i], nums[i], Color.BLACK, Color.BLUE, controller));
			}	
			
			/** to make calculator accept from keyboard input */
			this.getActionMap().put(nums[i], new KeyActions((JButton)keypad.getComponent(i)));
			this.getInputMap().put(KeyStroke.getKeyStroke(keyEvents[i], 0), nums[i]);
		}

		clear.setActionCommand("C");
		clear.setForeground(Color.BLACK);
		clear.setBackground(Color.RED);
		clear.addActionListener(controller); 
		operator.add(clear);
		/** to make calculator accept from keyboard input */
		this.getActionMap().put("C", new KeyActions(clear));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "C");
				
		equal.addActionListener(controller);
		equal.setActionCommand("=");
		equal.setForeground(Color.BLACK);
		equal.setBackground(Color.MAGENTA);
		operator.add(equal);  
		/** to make calculator accept from keyboard input */
		this.getActionMap().put("=", new KeyActions(equal));
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "=");
				
		add(north, BorderLayout.NORTH);   
		add(keypad, BorderLayout.CENTER);  
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
	 * @return buttons - the reference to the created button
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
 
		buttons.setFont(new Font(buttons.getFont().getName(), buttons.getFont().getStyle(), 20)); 
		buttons.addActionListener(handler);

		return buttons;
	}
	
	/**
	 * To display the textField and show other features 
	 * based on the number on floating point or integer mode
	 * 
	 * @param enable - true or false when choose integer or float mode 
	 */
	public void display(boolean enable) {
		/** If user clicked the floating point mode */
		if (enable) {
			dotButton.setEnabled(enable);
			dotButton.setBackground(Color.BLUE);
			error.setText("F");
			error.setBackground(Color.YELLOW);
		} else {
			 /** If user clicked "Int" mode */
			dotButton.setEnabled(enable);
			dotButton.setBackground(new Color(178, 156, 250));
			error.setText("I");
			error.setBackground(Color.GREEN);
		}
	}

	/**
	 * This class is a private inner class inside the CalculatorViewController class
	 * to handle the ActionListener on calculation.
	 *  
	 * @author Wenjing Wang
	 * @version 1
	 * @see javax.swing.JPanel
	 * @since 1.8.0_121
	 *
	 */
	private class Controller implements ActionListener {
		/** reference of CalculatorModel class */
		CalculatorModel cmodel; 
		/** the flag for calculator in default state */
		boolean defaultFlag = true; 
		/** the flag for calculator in integer mode */
		boolean integerFlag = false; 
		/** the flag for calculator if it is the override state */
		boolean overrideFlag = true;
		/** the flag for calculator if number in float mode */
		boolean floatFlag = false; 
		/** the boolean to set for backspace */
		boolean backspaceEnabled=false;
	
		/**
		 * Constructor to get instance from CalculatorModel class
		 * @param cmodel - a reference of CalculatorModel class
		 */
		public Controller(CalculatorModel model) {
			this.cmodel = model;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			boolean errors = false;
			
			/** The different case when user clicked buttons and handle the events */
			switch(event.getActionCommand()) {
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "0":
				/** To handle events when numbers is clicked */
				if (!cmodel.getError()) {
					numbers(event.getActionCommand());
					/** It handles error state after handling the action */
					if (cmodel.getError())
						errors = true;
				}
				backspaceEnabled=true;
				break;
							
			case "*":
			case "/":
			case "+":
			case "-":
				/** To handle events when operators is clicked */
				if (!cmodel.getError()) {
					operations(event.getActionCommand());
					floatFlag = false;
					/** It handles error state after handling the action */
					if (cmodel.getError())
						errors = true;
				}
				backspaceEnabled=true;
				break;
				
			case "=":
				/** To handle events when equal sign is clicked */
				if (!cmodel.getError()) {
					equals();					 
				}
				
				if (cmodel.getError()) {
					errors = true;
				}
				backspaceEnabled=false;
				break;
					
			case ".":
				/** To handle events when dot operators is clicked */
				if (!cmodel.getError()) {  
					if (!floatFlag) {
						if (overrideFlag) {
							display2.setText(".");
							overrideFlag = false;
							defaultFlag = false;
						} else {
							display2.setText(display2.getText().concat("."));
						}
						floatFlag = true;
					}
				}
				break;
				
			case "\u21B2":
				/** To handle events when backspace is clicked */
				if(!backspaceEnabled) return;
				if (!cmodel.getError()) {
					/** If not in default state for the display */
					if (!overrideFlag) {
						/** the situation that there are more than one number, remove from the end digit */
						if (display2.getText().length() >= 1) { 
							
							/** ??? When "=" is clicked, backspace is not operational */
							// o set textField to substring of the number */
								display2.setText(display2.getText().substring(0, display2.getText().length() - 1)); 
							
							
							/** If the last is an "-" operator, clear the textField */
							if (display2.getText().length() == 1 && display2.getText().contains("-")) {
								defaultDisplay(); 
								cmodel.clear();
								/** To reset the flag in default display */
                                overrideFlag = true;
                                floatFlag = false;
                                defaultFlag = true;
							}
						} else {
							defaultDisplay(); 
							cmodel.clear();
							/** To reset the flag in default display */
                            overrideFlag = true;
                            floatFlag = false;
                            defaultFlag = true;
						}
					}
					/** It handles error state after handling the action */
					if (cmodel.getError()) {
						errors = true;
					}
				}
				break;
				
			case "\u00B1":
				/** To handle events when plus minus sign is clicked */
				if (!cmodel.getError()) {
					/** To handle the plus minus sign to convert a number */
					if (display2.getText().startsWith("-")) {
						display2.setText(display2.getText().substring(1));
					} else {
						display2.setText("-".concat(display2.getText()));
					}
					/** It handles error state after handling the action */
					if (cmodel.getError()) {
						errors = true;
					}
				}
				backspaceEnabled=true;
				break;
				
			case "C":
				/** To handle events when "C" is clicked */
				if (cmodel.getError()) {
					 display(!cmodel.integerMode());
				}
				/** To reset the display in textField */
				defaultDisplay();
				cmodel.clear(); 
				
				/** To reset the flag in default display */
                overrideFlag = true;
                floatFlag = false;
                defaultFlag = true;
				break;
		
			case ".0":
				/** To handle events when choose .0 mode */
				integerFlag = false;
				cmodel.setPrecision(CalculatorModel.FP_PRECISION_0);
				cmodel.setOperationMode(CalculatorModel.FLOAT);
				
				if (!cmodel.getError()) {
					display(true);
					/** To convert a number to different mode */
					defaultDisplay(); 
				}
				break;
				
			case ".00":
				/** To handle events when choose .00 mode */
				integerFlag = false;
				cmodel.setPrecision(CalculatorModel.FP_PRECISION_00);
				cmodel.setOperationMode(CalculatorModel.FLOAT);
				
				if (!cmodel.getError()) {
					display(true);
					/** To convert a number to different mode */
					defaultDisplay();	 
				}
				break;
				
			case "Sci":
				/** To handle events when choose Sci mode */
				integerFlag = false;
				cmodel.setPrecision(CalculatorModel.FP_PRECISION_SCI);
				cmodel.setOperationMode(CalculatorModel.FLOAT);
				
				if (!cmodel.getError()) {
					display(true);
					/** To convert a number to different mode */
					defaultDisplay(); 
				}
				break;
				
			case "Int":
				/** To re-click the checkBox */ 
			    if (integerFlag) { 
			    	doubleJRadioButton.doClick(); 
			    	break;
			    }  
				
			    /** set the "Int" is clicked */
				integerFlag = true; 
				cmodel.setOperationMode(CalculatorModel.INTEGER);
				
				if (!cmodel.getError()) {
					display(false);
					/** To convert a number to different mode */
					defaultDisplay();
				}
				break;
				
			default:
				break;
			}
			
			if (errors) {
				error.setText("E");
				error.setBackground(Color.RED); 
				display2.setText(cmodel.getErrorMessage());
				cmodel.clearErrorMessage();			
			}
		
		} 		
		
		/**
		 * Handle the numbers that user entered into textField.
		 * @param s - the number user entered 
		 */ 
		public void numbers(String s) {
			/** To make sure the number digits entered less than the width of the calculator */
			if (display2.getText().length() < display2.getColumns() && display1.getText().length() < display1.getColumns()) {
				
				/** To display the numbers on the textField based on op1 and op2 */
				switch(cmodel.getState()) {
				case CalculatorModel.OPERAND_1:
					if (overrideFlag) {
						display2.setText(s); 
						overrideFlag = false;
						defaultFlag = false;
						break;
					} 
					display2.setText(display2.getText().concat(s));
					break;
					
				case CalculatorModel.OPERAND_2: 
					if (overrideFlag) {
						display2.setText(s);
						overrideFlag = false; 
						break;
					}
					display2.setText(display2.getText().concat(s));
					break;
					
				case CalculatorModel.RESULT:
					display1.setText("");
					display2.setText(s);
					break;
					
				default:
					cmodel.setError(true);
					break;
				}	
			} else {
				/** If longer than the current width of the display, not display */
				display2.setText("");
				cmodel.clear();
			}
		}
		
		/**
		 * To handle the event that "=" sign is clicked by user. 
		 */
		public void equals() {
			/** To calculate and display the result on the textField */
			switch(cmodel.getState()) {					
			case CalculatorModel.OPERAND_2: 
				cmodel.setOperand2(display2.getText());				
				String results = cmodel.getResult();
				
				if (results != null) {
					display1.setText("");
					display2.setText(results);  
					
					/** If the result longer than the current width of the display, not display */
					if (display2.getText().length() > display2.getColumns()) { 
						display2.setText("");
						cmodel.clear();
					}					
					break;
				} 
				cmodel.setError(true);
				break;
				
			case CalculatorModel.RESULT:
				String re = cmodel.getResult();
				display2.setText(re);
				break;
				
			default:
				cmodel.setError(true);
				break;
			}	
		}
		
		/**
		 * To handle the event that arithmetic operation is clicked by user.
		 * @param s - arithmetic operation 
		 */
		public void operations(String s) { 
			/** To display the numbers and the operator on the textField */
			switch(cmodel.getState()) {
			case CalculatorModel.OPERAND_1:
				overrideFlag = true;
				
				cmodel.setOperand1(display2.getText());
				cmodel.setOperation(s); 
				display1.setText(display2.getText().concat(s)); 
				break;
				
			case CalculatorModel.OPERAND_2:
				if (overrideFlag) { 
					cmodel.setOperation(s);  
					display1.setText(display2.getText().concat(s));
					break;
				} 
				break;
				
			case CalculatorModel.RESULT:
				cmodel.setOperation(s);
				display1.setText(display2.getText().concat(s));
				display2.getText();
				overrideFlag = true;
				break;
				
			default:
				cmodel.setError(true);
				break;
			}	
		}
		
		/**
		 * To reset the display based on the mode user entered 
		 */
		public void defaultDisplay() {
			/** If in integer mode, everything will be 0; else be .00 in float mode */
			if (cmodel.integerMode()) {
				display1.setText(" ");
				display2.setText("0");
			} else {
				display1.setText(" ");
				display2.setText("0.0");
			}
			overrideFlag = true;
		}		
		
	}  
	
	/**
	 * The KeyActions class to make calculator accept keyboard
	 * input.
	 * 
	 * @author Wenjing Wang
	 * @version 1
	 * @see javax.swing.AbstractAction
	 * @since 1.8.0_121
	 */
	private class KeyActions extends AbstractAction {
		/** the button to handle keyboard actions */
		AbstractButton button;
		
		/**
		 * Constructor to take the AbstractAction as a reference
		 * @param button 
		 */
		public KeyActions(AbstractButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			button.doClick();
		}
	}
}