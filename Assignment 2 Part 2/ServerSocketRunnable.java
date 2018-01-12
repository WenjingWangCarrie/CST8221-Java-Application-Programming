/**
 * File Name:       ServerSocketRunnable.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      2, Part 2
 * Date:            December 28, 2017
 * Professor:       Svillen Ranev
 * Purpose:         ServerSocketRunnable class is responsible for communicating with
 *                  the client and responding to the command strings sent by the client. 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * This class is responsible for communicating with the client.
 * 
 * @author Wenjing Wang
 */
public class ServerSocketRunnable implements Runnable {

    /** The supported 'quit' command in the implementation */
    private static final String QUIT = "quit";
    /** The supported 'echo' command in the implementation */
    private static final String ECHO = "echo";
    /** The supported 'time' command in the implementation */
    private static final String TIME = "time";
    /** The supported 'date' command in the implementation */
    private static final String DATE = "date";
    /** The supported 'help' command in the implementation */
    private static final String HELP = "help";
    /** The supported 'clrs' command in the implementation */
    private static final String CLRS = "clrs";
    /** Socket object instance */
    private Socket socket = null;
    
    /** String months in a year */
    private final String months[] = {"January", "February", "March", "April", "May", "June", "July", 
        "August", "September", "October", "November", "December"};

    /**
     * Initializes the ServerSocketRunnable instance.
     *
     * @param socket
     */
    public ServerSocketRunnable(Socket socket) {
        this.socket = socket;
    }

    /**
     * Performs tasks that read client's command and server respond messages. 
     */
    public void run() {
        /** input reader for server to listen to the command */
        BufferedReader in = null;
        /** output writer to send message to clients */
        PrintWriter out = null;
        /** command message that user entered */
        String message = null;
        /** optional message after supported command */
        String optional = null;
        /** Calendar used to calculate the time */
        LocalDateTime time = null;
        /** Calendar used to calculate the date */
        LocalDateTime date = null;

        /** check if the socket is connected */
        if (socket == null) {
            System.out.println("Connection with client falied.");
            return;
        }

        /** create input reader and output writer to the socket */
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Error to retrieve stream information from client.");
            return;
        }

        try {
            /** In a loop that repeats until the quit command is sent by the client */
            while ((message = in.readLine()) != null) {
                /** extracts the valid COMMAND from the message string */
                if (message.startsWith("-")) {
                    String[] parts = message.substring(1).split("-");
                    String command = parts[0];

                    /** If the COMMAND is echo */
                    if (command.equals(ECHO)) {
                        optional = (parts.length >= 2) ? parts[1] : "";
                        out.println("SERVER>ECHO:" + optional);
                    }
                    /** If the COMMAND is time */
                    else if (command.equals(TIME)) {
                        time = LocalDateTime.now();
                            
                        out.println(String.format("SERVER>TIME: %2d:%2d:%2d %s", time.getHour(),
                            time.getMinute(), time.getSecond(), time.getHour() > 11 ? "PM" : "AM"));
                    }
                    /** If the COMMAND is date */
                    else if (command.equals(DATE)) {
                        date = LocalDateTime.now(); 
                            
                        out.println("SERVER>DATE: " + date.getDayOfMonth() + " " 
                            + months[date.getMonthValue()-1] + " " + date.getYear());
                    }
                    /** If the COMMAND is help */
                    else if (command.equals(HELP)) {
                        out.println("SERVER>Available Services:#" + "quit#" + "echo#" + "time#"
                                + "date#" + "help#" + "clrs#");
                    }
                    /** If the COMMAND is clrs */
                    else if (command.equals(CLRS)) {
                        out.println("SERVER>CLS:");
                    }
                    /** If the COMMAND is time */
                    else if (command.equals(QUIT)) {
                        out.println("SERVER>Connection closed.");
                        break;
                    }
                    /** Unrecognized command */
                    else {
                        out.println("SERVER>ERROR: Unrecognized command.");
                    }

                    /** the tread sleeps for 100 milliseconds */
                    Thread.sleep(100);
                }
            } 
        } catch (Exception e) {
            System.out.println("Thread sleep failed - " + e.getMessage());
        } finally {
            try {
                /** close the socket */
                if (socket != null)
                    socket.close();

                /** close the inputStream */
                if (in != null)
                    in.close();

                /** close the outputStream */
                if (out != null) {
                    out.flush();
                    out.close();
                }
                
                System.out.println("Server Socket: Closing client connection...");
            } catch (IOException e) {
                System.out.println("Failed to close socket, input reader or output writer.");
            }
   
        }
    }
}
