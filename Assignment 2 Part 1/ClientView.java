/**
 * File Name:       ClientView.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      2, Part 1
 * Date:            December 18, 2017
 * Professor:       Svillen Ranev
 * Purpose:         This class is responsible for building a client GUI.             
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets; 

import javax.swing.BorderFactory; 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * This class is used to build a relatively simple GUI for the
 * Client Application.
 * 
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8.0_121
 * @author Wenjing Wang
 */
public class ClientView extends JPanel {
	/** the combo box holds the port numbers */
	private JComboBox<String> combo;
	/** the text area for the terminal */
	private JTextArea terminal;
	/** the text area for the client request */
	private JTextArea request;
	/** the text field for the host in the connection */
	private JTextField host;
	/** the button to connect with server */
	private JButton connect;
	/** the send button for the client request */
	private JButton send;

	/**
	 * This default constructor to initialize the view panel.
	 */
	public ClientView() {
		/** the ports number in the Port caret */
		String ports[] = new String[] {"", "8089", "65001", "65535"};
		/** host label */
		JLabel hostLabel = new JLabel("Host:");
		/** port label */
		JLabel portLabel = new JLabel("Port:");
		/** connection panel */
		JPanel connectionPanel = new JPanel(new BorderLayout());
		/** client request panel */
		JPanel requestPanel = new JPanel(new BorderLayout()); 
		/** terminal panel holds text area and scroll pane */
		JPanel terminalPanel = new JPanel(new BorderLayout());
		/** top panel to holds the connection and request panel */
		JPanel topPanel = new JPanel(new BorderLayout()); 
		/** the scroll pane for terminal panel */
		JScrollPane scrollPane;
		/** Grid bag properties */
		GridBagConstraints bags = new GridBagConstraints();
		/** the panel to group connections part */
		JPanel connection = new JPanel(new GridBagLayout());
		/** the panel to group request part */
		JPanel requestion = new JPanel(new GridBagLayout());
	
		/** set the whole layout and border */
		setLayout(new BorderLayout()); 
	    setBorder(BorderFactory.createEmptyBorder(4, 2, 4, 2));
		
		/** set the properties of host label */	   
		hostLabel.setDisplayedMnemonic('H');  
		hostLabel.setPreferredSize(new Dimension(40, 20));
		
		/** set the properties of host text field */
		host = new JTextField("localhost"); 
		host.setAlignmentX(Component.LEFT_ALIGNMENT);
		host.setBackground(Color.WHITE);
		host.setEditable(true); 
		hostLabel.setLabelFor(host);
		host.setMargin(new Insets(0, 5, 0, 0)); 
	 
		/** set the properties of port label */
		portLabel.setDisplayedMnemonic('P'); 
		portLabel.setPreferredSize(new Dimension(40, 40));
				
		/** set the combo box for port numbers */
		combo = new JComboBox<String>(ports);
		combo.setMaximumRowCount(4);
		combo.setPreferredSize(new Dimension(100, 20));
		combo.setAlignmentX(Component.LEFT_ALIGNMENT);
		combo.setBackground(Color.WHITE);
		combo.setEditable(true);
		portLabel.setLabelFor(combo);
			
		/** set the connect button */
		connect = new JButton("Connect");
		connect.setMnemonic('C');
		connect.setBackground(Color.RED);
		connect.setPreferredSize(new Dimension(100, 20));
		
		/** set the client request text area */
		request = new JTextArea("Type a request command");
		request.setPreferredSize(new Dimension(400, 20));
		request.setAlignmentX(Component.LEFT_ALIGNMENT);
		request.setBackground(Color.WHITE);
		request.setEditable(true);
		
		/** set the send button */
		send = new JButton("Send");
		send.setMnemonic('S');
		send.setEnabled(false);
		send.setPreferredSize(new Dimension(70, 20));
		
		/** set the terminal text area */
		terminal = new JTextArea();
		terminal.setEditable(false);
		terminal.setRows(50);
		terminal.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane = new JScrollPane(terminal);
		
		/** to set the title border for each panel */
		connectionPanel.setBorder(
	    		BorderFactory.createTitledBorder(
	    				BorderFactory.createLineBorder(Color.RED, 10), "CONNECTION"));
		
		requestPanel.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLUE, 10), "CLIENT REQUEST"));
		
		terminalPanel.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createLineBorder(Color.BLACK, 10), "TERMINAL", TitledBorder.CENTER, TitledBorder.CENTER));
		
		/** to add components into grid bag */
        bags.gridx = 0;
        bags.gridy = 0;
        bags.anchor = GridBagConstraints.LINE_START;
        bags.insets = new Insets(5, 5, 5, 5);
        connection.add(hostLabel, bags);
        
        bags.gridx = 1;
        bags.gridy = 0;
        bags.weightx = 1;
        bags.fill = GridBagConstraints.HORIZONTAL;
        bags.gridwidth = 2; 
        bags.insets = new Insets(0, 0, 0, 5);
        connection.add(host, bags);
        
        bags.gridx = 0;
        bags.gridy = 1;
        bags.weightx = 0;
        bags.fill = GridBagConstraints.NONE;
        bags.gridwidth = 1;
        bags.insets = new Insets(0, 4, 0, 5);
        connection.add(portLabel, bags);
        
        bags.gridx = 1;
        bags.gridy = 1;
        bags.insets = new Insets(0, 0, 0, 5);
        connection.add(combo, bags);
        
        bags.gridx = 2;
        bags.gridy = 1;
        bags.insets = new Insets(0, 4, 0, 0);
        connection.add(connect, bags);
        connection.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
        /** to add components to client request grid bag */
        bags.anchor = GridBagConstraints.LINE_START;
        bags.gridx = 0;
        bags.gridy = 0;
        bags.weightx = 0;
        bags.fill = GridBagConstraints.HORIZONTAL;
        bags.insets = new Insets(4, 0, 4, 4);
        requestion.add(request, bags);
        bags.gridx = 1;
        bags.gridy = 0;
        bags.weightx = 0;
        bags.insets = new Insets(4, 0, 4, 0);
        requestion.add(send, bags);
        requestion.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));

		/** to add the panels into the whole panel */
        connectionPanel.add(connection, BorderLayout.NORTH);
		topPanel.add(connectionPanel, BorderLayout.NORTH);
		topPanel.add(requestPanel, BorderLayout.SOUTH);
		terminalPanel.add(scrollPane, BorderLayout.CENTER);
		requestPanel.add(requestion, BorderLayout.WEST);
		
		add(topPanel, BorderLayout.NORTH); 
		add(terminalPanel, BorderLayout.CENTER);
	}
}
