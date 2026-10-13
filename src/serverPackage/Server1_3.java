package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("IP de CETTE machine  : ");
        String ip = sc.nextLine().trim();
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(ip), port));
            System.out.println("Serveur en écoute sur " + ip + ":" + port);

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté : "
                    + socket.getInetAddress().getHostAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int choix;
            do {
                choix = in.readInt();
                if (choix == 0) break;          // le client veut quitter

                double x = in.readDouble();
                double y = in.readDouble();
                String reponse;

                switch (choix) {
                    case 1: reponse = "Résultat = " + (x + y); break;
                    case 2: reponse = "Résultat = " + (x - y); break;
                    case 3: reponse = "Résultat = " + (x * y); break;
                    case 4:
                        if (y == 0) reponse = "Erreur : division par zéro";
                        else        reponse = "Résultat = " + (x / y);
                        break;
                    default: reponse = "Erreur : opération inconnue";
                }
                out.writeUTF(reponse);
                out.flush();
                System.out.println("Opération " + choix + " : " + reponse);
            } while (true);

            System.out.println("Session terminée.");
            socket.close();

        } catch (EOFException e) {
            System.out.println("Le client s'est déconnecté brutalement.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}