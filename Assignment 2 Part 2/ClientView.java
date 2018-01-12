/**
 * File Name:       ClientView.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      2, Part 2
 * Date:            December 28, 2017
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 * This class is used to build a relatively simple GUI for the Client
 * Application.
 * 
 * @author Wenjing Wang
 */
public class ClientView extends JPanel implements ActionListener {

    /** The combo box holds the port numbers */
    private JComboBox<String> combo;
    /** The text area for the terminal */
    private JTextArea terminal;
    /** The text area for the client request */
    private JTextArea request;
    /** The text field for the host in the connection */
    private JTextField host;
    /** The button to connect with server */
    private JButton connect;
    /** The send button for the client request */
    private JButton send;
    /** The client socket runnable class */
    private ClientSocketRunnable csr;

    /** The communication thread */
    private Thread communicationThread = null;

    /** The BlockingQueue to hold commands */
    private BlockingQueue<String> queue = null;

    /**
     * Default constructor to initialize the view panel.
     */
    public ClientView() {
        /** the ports number in the Port caret */
        String ports[] = new String[] { "", "8089", "65001", "65535" };
        /** host label **/
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
        connect.addActionListener(this);
        connect.setActionCommand("CONNECT");

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
        send.addActionListener(this);
        send.setActionCommand("SEND");

        /** set the terminal text area */
        terminal = new JTextArea();
        terminal.setEditable(false);
        terminal.setRows(50);
        terminal.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane = new JScrollPane(terminal);

        /** set the title border for each panel */
        connectionPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 10), "CONNECTION"));

        requestPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE, 10), "CLIENT REQUEST"));

        terminalPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 10),
                "TERMINAL", TitledBorder.CENTER, TitledBorder.CENTER));

        /** add components into grid bag */
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

        /** add components to client request grid b */
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

        /** add the panels into the whole panel */
        connectionPanel.add(connection, BorderLayout.NORTH);
        topPanel.add(connectionPanel, BorderLayout.NORTH);
        topPanel.add(requestPanel, BorderLayout.SOUTH);
        terminalPanel.add(scrollPane, BorderLayout.CENTER);
        requestPanel.add(requestion, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);
        add(terminalPanel, BorderLayout.CENTER);
    }

    /**
     * Handles the events from client. 
     *
     * @param e - ActionEvent to handle
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "CONNECT":
                int port;
                String hostName = host.getText();
                Socket socket;

                /** if host name is not exist */
                if (hostName.isEmpty()) {
                    return;
                }

                /** to parse the port string type into int type */
                try {
                    port = Integer.parseInt((String) combo.getSelectedItem());
                } catch (NumberFormatException num) { 
                    return;
                }

                try {
                    /** connect with server - a timeout socket connection */
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(InetAddress.getByName(hostName), port), 10000);
                    socket.setSoTimeout(10000);
                } catch (UnknownHostException un) {
                    terminal.append("CLIENT>ERROR: Unkown host.\n");
                    return;
                } catch (IllegalArgumentException ill) {
                    terminal.append(
                        "CLIENT>ERROR: Connection refused: server is not avaliable. Check port or restart server.\n");
                    return;
                } catch (IOException io) {
                    terminal.append(
                        "CLIENT>ERROR: Connection refused: server is not avaliable. Check port or restart server.\n");
                    return;
                }

                terminal.append(String.format("Connecting to Socket[addr=%s, port=%d, localport=%d]\n",
                    socket.getInetAddress(), socket.getPort(), socket.getLocalPort()));
                queue = new ArrayBlockingQueue<String>(256);
                csr = new ClientSocketRunnable(queue, socket);
                communicationThread = new Thread(csr);
                communicationThread.start();
                break;

            case "SEND":
                /** client send request to server */
                if (!request.getText().isEmpty()) {
                    csr.sendRequest(request.getText());
                }
                break;

            default:
                break;
        }
    }

    /**
     * The appearance when connected with server.
     *
     * @param connected - true(connected with server) 
     */
    public void setConnect(boolean connected) {
        SwingUtilities.invokeLater(() -> {
            if (connected) {
                connect.setBackground(Color.BLUE);
                connect.setEnabled(false);
                send.setEnabled(true);
            } else {
                connect.setBackground(Color.RED);
                connect.setEnabled(true);
                send.setEnabled(false);
            }
        });
    }

    /**
     * Displays messages in the terminal area when connected with server.
     *
     * @param s - messages should displayed on the terminal area 
     */
    public synchronized void displayTerminal(String s) {
        SwingUtilities.invokeLater(() -> {
            String[] segments = s.split("#"); 
            for (String segment : segments) {
                terminal.append(segment + "\n"); 
            } 
        });
    }
    
    /**
     * To add a new line in the terminal area when connected with server.
     *
     * @param s - messages should displayed on the terminal area 
     */
    public synchronized void addLineTerminal(String s) {
        SwingUtilities.invokeLater(() -> {
            terminal.append("\n");
        });
    }

    /**
     * Clears the terminal area   
     */
    public void clearTerminal() {
        SwingUtilities.invokeLater(() -> {
            terminal.setText(" ");
        });
    }

    /**
     * This class is the private inner class that communicate with client.
     * 
     * @author Wenjing Wang
     * @version 1
     * @see javax.lang.Runnable
     * @since 1.8.0_121 
     */
    private class ClientSocketRunnable implements Runnable {
        /** BlockingQueue instance to receive commands */
        private BlockingQueue<String> queue = null;
        /** Socket instance to connect with client */
        private Socket socket = null;
        /** the response message to client */
        private String response;
        /** input reader for server to listen to the command */
        BufferedReader in = null;
        /** output writer to send message to clients */
        PrintWriter out = null;

        /**
         * An instructor using the socket instance and BlockingQueue instance object.
         *
         * @param socket - socket instance object 
         * @param queue - BlockingQueue instance object
         */
        public ClientSocketRunnable(BlockingQueue<String> queue, Socket socket) {
            this.queue = queue;
            this.socket = socket;
        }

        /**
         * Displays messages in the terminal area when connected with server 
         */
        public void run() {
            /** Check if socket is not available */
            if (socket == null) {
                return;
            }

            /** Create input and output streams to the socket */
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                displayTerminal("ERROR: Could not connect to server");
                return;
            }

            setConnect(true);

            /** an endless loop to respond message to client */
            while (true) {
                try {
                    /** take a message from the queue */
                    String message = queue.take();

                    /** send the message to the server */
                    out.println(message);

                    /** wait for the server's response */
                    response = in.readLine();

                    /** Check respond message exist */
                    if (response != null) {
                        /** To display messages based on the server state */
                        if (response.equals("SERVER>Connection closed.")) {
                            displayTerminal("SERVER>Connection closed.");
                            break;
                        } else if (response.equals("SERVER>CLS:")) {
                            clearTerminal();
                        } else if (response.equals("SERVER>Available Services:#" + "quit#" + "echo#" + "time#"
                                + "date#" + "help#" + "clrs#")) { 
                        	displayTerminal(response);
                        	addLineTerminal("\n");
                        } else { 
                            displayTerminal(response);
                        }
                    } else {
                        break;
                    }
                } catch (InterruptedException e) {
                    break;
                } catch (IOException e) {
                    displayTerminal("ERROR: Connection with server lost.");
                    break;
                }
            }

            displayTerminal("CLIENT>Connection closed.");

            /** to clean up server sockets and input, output */
            try {
                /** if the socket is not closed */
                if (socket != null) {
                    socket.close();
                }

                /** if the reader is not closed */
                if (in != null) {
                    in.close();
                }

                /** if writer is not closed */
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                displayTerminal("ERROR: could not clean up!");
            } finally {
                setConnect(false);
            }
        }

        /**
         * Writes the specified message to the queue
         *
         * @param request - request information in the text field
         */
        public void sendRequest(String request) {
            if (request != null && !request.isEmpty()) {
                try {
                    queue.put(request);
                } catch (InterruptedException e) {
                    displayTerminal("ERROR: could not put the message into the queue.");
                }
            }
        }
    }
}
