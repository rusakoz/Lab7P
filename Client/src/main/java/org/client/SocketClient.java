package org.client;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Getter
@Setter
public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(org.server.Main.class);
    private Socket socket;
    private String hostname;
    private int port;

    private Dotenv dotenv = Dotenv.load();
    public SocketClient(){
        this.hostname = dotenv.get("host");
        this.port = Integer.parseInt(dotenv.get("port"));
    }

    public Socket connect() throws IOException{

        Socket clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress(hostname, port), 2000);

        return clientSocket;
    }

    public void answer() throws IOException {
        socket = connect();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

    }

    public void priem() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        close();
    }

    public final void close() throws IOException {
        if (socket != null) socket.close();
    }

}
