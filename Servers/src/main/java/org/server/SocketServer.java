package org.server;

import io.github.cdimascio.dotenv.Dotenv;
import org.server.Analyze.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedHashMap;

public class SocketServer {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private final int port;
    private final Dotenv dotenv = Dotenv.load();
    private ServerSocket serverSocket;

    public SocketServer() {
        this.port = 7778;
    }

    public void openSocket() {
        try {
            serverSocket = new ServerSocket(port);
            //serverSocket.setSoTimeout(20000);
            logger.info("OpenSocket");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void startAccept() throws IOException, ClassNotFoundException {

        while(true) {
            logger.info("Server waiting...");
            //try {
                Socket clientSocket = serverSocket.accept();
            //}catch (SocketTimeoutException e){
                //logger.info("Сервер не получил запрос");
            //}
            logger.info("Server accepted new packet");

            System.out.println(clientSocket);
            Other other = new Other(18);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(objectInputStream);
            System.out.println("dasdas");


            //ObjectToSend kto = (ObjectToSend) objectInputStream.readObject();
            //LinkedHashMap<String, Integer> map = (LinkedHashMap<String, Integer>) kto.getObject();
            //Flat flat = (Flat) kto.getObject();
            //logger.info("Prishlo {} {}", kto.getNameCommand(), flat.getName());
            //map.forEach((key, value) -> System.out.println(key + " " + value));


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutputStream.writeObject(new Analyzer().executor(objectInputStream));
            logger.info("Obj otpravlen");
;
        }
    }

    public void answer(Object obj){
        //ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        //objectOutputStream.writeObject(obj);
        //logger.info("Obj otpravlen");
    }

    public void close() throws IOException {
        if (serverSocket != null) serverSocket.close();
    }
}
