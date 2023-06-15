package org.server;

import org.server.Analyze.CollectionManager;
import org.server.DataBase.DatabaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainServer {
    private static final Logger logger = LoggerFactory.getLogger(MainServer.class);

    public static void main(String[] args) {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.insert("Dima");
        System.out.println(databaseHandler.select());


        /*CollectionManager a = new CollectionManager();
        a.Read();

        SocketServer socketServer = new SocketServer();
        socketServer.openSocket();
        while (true) {
            try {
                socketServer.startAccept();
            } catch (IOException | ClassNotFoundException e) {
                logger.warn("WARNING {}", e.getMessage());
                e.printStackTrace();
            }
        }*/
    }
}
