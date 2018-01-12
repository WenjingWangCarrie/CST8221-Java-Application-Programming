/**
 * File Name:       Server.java
 * Author:          Wenjing Wang, 040812907
 * Course:          CST8221 - JAP, Lab Section: 303
 * Assignment:      2, Part 2
 * Date:            December 28, 2017
 * Professor:       Svillen Ranev
 * Purpose:         This class is responsible for creating a server socket 
 *                  and starting a service thread responsible for serving
 *                  each individual client. 
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is responsible for creating a server socket and start service
 * thread.
 * 
 * @author Wenjing Wang
 */
public class Server {

    public static void main(String args[]) { 
        int port = 65535;

        /** If command line string is supplied at launch */
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            System.out.println("Using port: " + port);
        } else {
            System.out.println("Using default port: " + port);
        }

        /** create a thread object to manage the server threads */
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ServerSocket ss = null;

        try {
            /** create a server socket and bound to the specified port */
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Failed to create a ServerSocker at the port " + port);
            return;
        }

        while (true) {
            try {
                /** wait for the client request */
                Socket socket = ss.accept();

                /** connection with the client is established */
                System.out.printf("Connecting to a client Socket[addr=%s, port=%d, localport=%d]\n",
                    socket.getInetAddress(), socket.getPort(), socket.getLocalPort());

                ServerSocketRunnable ssr = new ServerSocketRunnable(socket);
                executor.execute(ssr);
            } catch (IOException e) { 
                System.out.println("Failed to accept a new connection");
            }
        }
    }
}
