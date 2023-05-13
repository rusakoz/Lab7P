package org.client;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import org.server.Other;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

@Getter
@Setter
public class SocketClient{
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private Socket socket;
    private String hostname;
    private int port;

    private final Dotenv dotenv = Dotenv.load();
    public SocketClient(){
        this.hostname = "127.0.0.1";
        this.port = 7777;
    }

    public Socket connect() throws IOException{

        Socket clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(hostname, port), 2000);

        return clientSocket;
    }

    public void answer(Object obj) throws IOException, ClassNotFoundException {
        socket = connect();

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
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Other other = (Other) objectInputStream.readObject();
        logger.info("Prishlo {}", other.getAge());

        objectInputStream.close();

    }

    public void close() throws IOException {
        if (socket != null) socket.close();
    }
}
