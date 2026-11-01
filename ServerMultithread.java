package tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

public class ServerMultithread {
	public static final int PORT = 1234;

    public static void main(String[] args) {
        int numClient = 0;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur démarré sur le port " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                numClient++;
                System.out.println("Client n°" + numClient + " connecté - IP : "
                        + socket.getRemoteSocketAddress());
                new ClientProcess(socket, numClient).start();
            }
        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
}
