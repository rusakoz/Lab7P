package org.client;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.server.ObjectToSend;
import org.server.Other;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

@Getter
@Setter
@EqualsAndHashCode
public class SocketClient{
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private Socket socket;
    private String hostname;
    private int port;
    private final int MAX_RECONNECTIONS = 10;
    private int reconnections;

    private final Dotenv dotenv = Dotenv.load();
    public SocketClient(){
        this.hostname = "127.0.0.1";
        this.port = 7777;
    }

    public void connect(){

        Socket clientSocket = new Socket();
        try {
            clientSocket.connect(new InetSocketAddress(hostname, port), 2000);

            socket = clientSocket;

        }catch (ConnectException e){
            logger.error("Ошибка подключения к серверу, извините :(");
            tryToReconnect();
        }catch (SocketTimeoutException e) {
            logger.error("Connection: " + e.getMessage() + ".");
            tryToReconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void answer(Object obj) throws IOException, ClassNotFoundException {

        connect();

        Kto kto = new Kto("Ruslan");
        logger.info("otpravka");
        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(obj);
        System.out.println(obj);
        logger.info("Obj otpravlen");

        priem();
        logger.info("Obj prishel");

        objectOutputStream.close();
        close();
    }

    public void priem() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            ObjectToSend other = (ObjectToSend) objectInputStream.readObject();
            logger.info("Prishlo {}", other.getNameCommand());

            StringBuilder str = (StringBuilder) other.getObject();
            logger.info("Prishlo {}", str);

            objectInputStream.close();
        }catch (SocketException e){
            logger.info("Сервер прилег отдохнуть, приносим извинения :(");
        }
    }

    private void tryToReconnect() {

        logger.info("Попытка подключения к серверу через 10 секунд... (" + reconnections + "/10)");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        if (reconnections < MAX_RECONNECTIONS) {
            reconnections++;
            connect();

        } else {
            logger.info("Не удалось подключиться к серверу, повторите попытку позже :(");
        }

    }

    public void close() throws IOException {
        if (socket != null) socket.close();
    }
}
