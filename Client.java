import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : MultiThreadServer.PORT;

        try (Socket socket = new Socket();
             Scanner sc = new Scanner(System.in)) {
            socket.connect(new InetSocketAddress(InetAddress.getByName(host), port), 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(in.readLine());   // message de bienvenue + numéro d'ordre
            while (true) {
                System.out.print("> ");
                String msg = sc.nextLine();
                out.println(msg);
                String rep = in.readLine();
                if (rep == null) break;
                System.out.println(rep);
                if (msg.trim().equalsIgnoreCase("exit")) break;
            }
        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}

