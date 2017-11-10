package Swing;

//Fig. 12.19: RadioButtonFrame.java
//Creating radio buttons using ButtonGroup and JRadioButton.
//Modified by S. Ranev
//version: 1.17.2
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class RadioButtonFrame extends JFrame {
	private static final long serialVersionUID = -1393252362168407983L;
	private final JTextField textField; // used to display font changes
	private final Font plainFont; // font for plain text
	private final Font boldFont; // font for bold text
	private final Font italicFont; // font for italic text
	private final Font boldItalicFont; // font for bold and italic text
	private final JRadioButton plainJRadioButton; // selects plain text
	private final JRadioButton boldJRadioButton; // selects bold text
	private final JRadioButton italicJRadioButton; // selects italic text
	private final JRadioButton boldItalicJRadioButton; // bold and italic
	private final ButtonGroup radioGroup; // buttongroup to hold radio buttons

	/** RadioButtonFrame constructor adds JRadioButtons to JFrame */
	public RadioButtonFrame() {
		super("RadioButton Test");
		setLayout(new FlowLayout()); // set frame layout

		textField = new JTextField("Watch the font style change", 25);
		// get the default font for the text field
		String defaultFont = textField.getFont().getName();
		add(textField); // add textField to JFrame

		// create radio buttons
		plainJRadioButton = new JRadioButton("Plain", true);// make this button
															// selected at start
		boldJRadioButton = new JRadioButton("Bold", false);
		italicJRadioButton = new JRadioButton("Italic", false);
		boldItalicJRadioButton = new JRadioButton("Bold/Italic", false);
		add(plainJRadioButton); // add plain button to JFrame
		add(boldJRadioButton); // add bold button to JFrame
		add(italicJRadioButton); // add italic button to JFrame
		add(boldItalicJRadioButton); // add bold and italic button

		// create logical relationship between JRadioButtons
		// placing the radio buttons in a group makes them mutualy exclusive
		radioGroup = new ButtonGroup(); // create ButtonGroup
		radioGroup.add(plainJRadioButton); // add plain to group
		radioGroup.add(boldJRadioButton); // add bold to group
		radioGroup.add(italicJRadioButton); // add italic to group
		radioGroup.add(boldItalicJRadioButton); // add bold and italic

		// create font objects
		plainFont = new Font(defaultFont, Font.PLAIN, 14);
		boldFont = new Font(defaultFont, Font.BOLD, 14);
		italicFont = new Font(defaultFont, Font.ITALIC, 14);
		boldItalicFont = new Font(defaultFont, Font.BOLD + Font.ITALIC, 14);
		textField.setFont(plainFont); // set initial font to plain

		// register events for JRadioButtons
		plainJRadioButton.addActionListener(new RadioButtonHandler(plainFont));
		boldJRadioButton.addActionListener(new RadioButtonHandler(boldFont));
		italicJRadioButton.addActionListener(new RadioButtonHandler(italicFont));
		boldItalicJRadioButton.addActionListener(new RadioButtonHandler(boldItalicFont));
	} // end RadioButtonFrame constructor

	/**
	 * private inner class to handle radio button events here you can use
	 * ItemListener (see original book code) or ActionListener
	 */
	private class RadioButtonHandler implements ActionListener {
		private final Font font; // font associated with this listener

		public RadioButtonHandler(Font f) {
			font = f; // set the font of this listener
		} // end constructor RadioButtonHandler

		// handle radio button events
		@Override
		public void actionPerformed(ActionEvent evt) {
			// get the action command - in this case it is the button label
			String actionCommand = evt.getActionCommand();// get the action
															// command
			if (actionCommand.equals("Plain") || actionCommand.equals("Bold") || actionCommand.equals("Italic")
					|| actionCommand.equals("Bold/Italic")) {

				textField.setFont(font); // set font of textField
			}
		} // end method itemStateChanged
	} // end private inner class RadioButtonHandler
} // end class RadioButtonFrame

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/