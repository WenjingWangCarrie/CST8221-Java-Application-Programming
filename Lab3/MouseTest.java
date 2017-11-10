
/* CST8221-JAP: LAB 03, Exercise 1
   File name: MouseTest.java
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Demonstrates different mouse events.
 * 
 * @version 1.17.2
 * @author Svillen Ranev
 * @since Java 1.2
 */
public class MouseTest extends JFrame
		implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener {
	// default serial version ID - Swing components implement the Serializable
	// interface
	// generate serialVersionUID here after the code has been completed and
	// working
	private static final long serialVersionUID = 1L;
	// fields declarations
	private JButton button;
	private JPanel pane;
	private static final String FRAME_TITLE = "Mouse Test";
	private final Icon buttonImage = new ImageIcon("happy.gif");

	/**
	 * Default constructor. Sets the frame properties and creates GUI.
	 */
	public MouseTest() {
		// Sets the GUI
		// set frame title
		super(FRAME_TITLE);
		// set frame width, height
		setSize(200, 200);
		createGUI();
	}

	/** Create and set up containers and components. */
	private void createGUI() {
		// Create a button with an image and text
		button = new JButton("Mouse Test Button", buttonImage);
		// Create a Tool Tip. Will show up when the mouse hovers over the button
		button.setToolTipText("Please play with the mouse");
		button.setPreferredSize(new Dimension(button.getText().length() * 20, 60));
		// Set the application frame to be the event handler
		// Register an ActionListener and set the class object(this) to be the
		// event handler for the button click
		button.addActionListener(this);

		/* Register the three Mouse Listeners and set the event handler here: */
		button.addMouseListener(this);
		button.addMouseMotionListener(this);
		button.addMouseWheelListener(this);

		pane = new JPanel();
		// set a border around the JPanel
		pane.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 25));

		// Add components to JPanel container.
		pane.add(button);

		// Install JPanel pane as the content pane of the frame
		this.setContentPane(pane);
		// The GUI is ready.
	}

	/**
	 * The main method.The GUI will start with the default Look and Feel - Metal
	 * Look and Feel Runs the application GUI in the event dispatch thread
	 * (EDT). Anonymous class is used to create a runnable object.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		// Make all components to configured by the event dispatch thread.
		// This is the thread that passes user provoked events such as mouse
		// clicks to
		// the GUI components which have registered listeners for the events.
		// The following code fragment forces the statements in the run() method
		// to be executed in the
		// event dispatching thread.
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MouseTest frame = new MouseTest();
				// set up the Close button (X) of the frame
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.pack();
				// make the GUI visible
				frame.setVisible(true);
			}
		});
	}

	/******************************
	 * Beginning of Event handling Section
	 ***********************/
	// Handle the button clicks.
	// Override and implement the actionPerformed() method of the Action
	// Listener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		// process the event
		System.out.println("Method actionPerformed called");
	}

	/**************************************************************/
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		System.out.println("Method mouseClicked called");
		System.out.println("Click count: " + e.getClickCount());

		
		if (e.getButton() == 1) {
			System.out.println("Left Mouse button clicked");
		} else if(e.getButton() == 3) {
			System.out.println("Right Mouse button clicked");
		} else {
			System.out.println("Middle Mouse button clicked");
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		System.out.println("Method mouseEntered called");
		System.out.println("Mouse location: X = " + e.getX() + " Y = " + e.getY());
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		System.out.println("Method mouseExited called");
		System.out.println("Mouse location: X = " + e.getX() + " Y = " + e.getY());
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		System.out.println("Method mousePressed called");
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		System.out.println("Method mouseReleased called");
	}

	/*******************************************************************/
	/* Below implement all the methods of the MouseMotionListener methods */
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		System.out.println("Method mouseDragged called");
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		System.out.println("Mouse location: X = " + e.getX() + " Y = " + e.getY());

	}

	/*******************************************************************/
	/* Below implement all the methods of the MouseWheelListener methods */
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("Method mouseWheelMoved called");

		int notches = e.getWheelRotation();
		
		if (notches < 0) {
			System.out.println("Mouse wheel moved UP " + (-notches) + " notch(es)");
		} else {
			System.out.println("Mouse wheel moved DOWN " + notches + " notch(es)");
		}
	}
	/******************************
	 * End of Event handling Section
	 *****************************/
}// end of class
