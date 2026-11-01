package tp3;

import java.io.*;
import java.net.Socket;

public class ClientProcess extends Thread {
    private final Socket socket;
    private final int numClient;

    public ClientProcess(Socket socket, int numClient) {
        this.socket = socket;
        this.numClient = numClient;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("Bienvenue ! Vous etes le client n°" + numClient + " (tapez 'exit' pour quitter)");

            String ligne;
            while ((ligne = in.readLine()) != null) {
                if (ligne.trim().equalsIgnoreCase("exit")) {
                    out.println("Au revoir client n°" + numClient);
                    break;
                }
                System.out.println("[Client n°" + numClient + "] a envoye : " + ligne);
                out.println("Echo (client n°" + numClient + ") : " + ligne);
            }
        } catch (IOException e) {
            System.err.println("Client n°" + numClient + " : " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("Client n°" + numClient + " deconnecte.");
        }
    }
}
