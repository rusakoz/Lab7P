package org.server;

import io.github.cdimascio.dotenv.Dotenv;
import org.client.Kto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private int port;
    private final Dotenv dotenv = Dotenv.load();
    private ServerSocket serverSocket;

    public SocketServer() {
        this.port = 7777;
    }

    public void openSocket() {
        try {
            serverSocket = new ServerSocket(port);
            logger.info("OpenSocket");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void startAccept() throws IOException, ClassNotFoundException {

        while(true) {
            logger.info("Server waiting...");
            Socket clientSocket = serverSocket.accept();
            logger.info("Server accepted new packet");

            System.out.println(clientSocket);
            Other other = new Other(18);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(objectInputStream);

            Ktos kto = (Ktos) objectInputStream.readObject();
            Flat flat = (Flat) kto.getObject();
            logger.info("Prishlo {} {}", kto.getNameCommand(), flat.getName());


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(other);
            logger.info("Obj otpravlen");
;
        }
    }

    public void answer(){
        //PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        //printWriter.println("rjwefasdfodjsfa");
    }

    public void close() throws IOException {
        if (serverSocket != null) serverSocket.close();
    }
}
