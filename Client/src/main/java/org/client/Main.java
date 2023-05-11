package org.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(org.server.Main.class);

    public static void main(String[] args) {

        try(Socket clientSocket = new Socket()){
            clientSocket.connect(new InetSocketAddress("127.0.0.1", 7777), 2000);
            Scanner scanner = new Scanner(clientSocket.getInputStream());

            System.out.println(scanner.nextLine());

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            printWriter.println("ky");
            printWriter.println("ky");
            printWriter.println("ky");
            printWriter.println("ky");
        } catch (ConnectException e){
            logger.warn("Failed to connect to the server");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
