package tp3;

import java.io.*;
import java.net.Socket;

public class CalculatriceClientProces extends Thread {
    private final Socket socket;
    private final int numClient;

    public CalculatriceClientProces(Socket socket, int numClient) {
        this.socket = socket;
        this.numClient = numClient;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject("Bienvenue ! Vous etes le client n°" + numClient);
            oos.flush();

            while (true) {
                Object recu = ois.readObject();
                if (!(recu instanceof Operation)) break;   // ex. "exit"
                Operation op = (Operation) recu;

                switch (op.getOperateur()) {
                    case '+': op.setResultat(op.getOperande1() + op.getOperande2()); break;
                    case '-': op.setResultat(op.getOperande1() - op.getOperande2()); break;
                    case '*': op.setResultat(op.getOperande1() * op.getOperande2()); break;
                    case '/':
                        if (op.getOperande2() == 0) op.setErreur("Division par zero !");
                        else op.setResultat(op.getOperande1() / op.getOperande2());
                        break;
                    default: op.setErreur("Operateur inconnu : " + op.getOperateur());
                }

                int c = CalculatriceServer.incrementerCompteur();  // section critique
                op.setCompteurGlobal(c);
                System.out.println("[Client n°" + numClient + "] " + op
                        + (op.getErreur() == null ? " = " + op.getResultat() : " -> " + op.getErreur())
                        + " | compteur global = " + c);

                oos.writeObject(op);
                oos.flush();
                oos.reset();
            }
        } catch (EOFException e) {
            // client parti
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client n°" + numClient + " : " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("Client n°" + numClient + " deconnecte.");
        }
    }
}
