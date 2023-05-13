package org.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        SocketServer socketServer = new SocketServer();
        socketServer.openSocket();
        while (true) {
            try {
                socketServer.startAccept();
            } catch (IOException | ClassNotFoundException e) {
                logger.warn("WARNING {}", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
