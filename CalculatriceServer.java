package tp3;

import java.net.ServerSocket;
import java.net.Socket;

public class CalculatriceServer {
    public static final int PORT = 1234;
    private static int compteurGlobal = 0;

    public static synchronized int incrementerCompteur() {
        compteurGlobal++;
        return compteurGlobal;
    }

    public static void main(String[] args) {
        int numClient = 0;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur calculatrice sur le port " + PORT + "...");
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