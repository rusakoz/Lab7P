package org.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(7777)){
            logger.info("Socket starting");
            while(true){
                logger.info("Server waiting...");
                Socket clientSocket = serverSocket.accept();
                logger.info("Server accepted new packet");
                PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                printWriter.println("rjwefasdfodjsfa");
                Scanner scanner = new Scanner(clientSocket.getInputStream());
                while (scanner.hasNextLine()){
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
