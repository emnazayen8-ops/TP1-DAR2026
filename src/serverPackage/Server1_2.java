package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_2 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente d'un client...");
            Socket socket = serverSocket.accept();
            System.out.println("Un client est connecté");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            int x;
            do {
                x = in.readInt();               // (c) réception (bloquant)
                int resultat = x * 5;           // (d) calcul côté serveur
                out.writeInt(resultat);         // (e) envoi du résultat
                out.flush();                    // force l'envoi immédiat
                System.out.println("Reçu : " + x + " -> envoyé : " + resultat);
            } while (x != 0);                   // 0 = fin de session

            System.out.println("Fin de session, fermeture.");
            in.close();
            out.close();
            socket.close();

        } catch (EOFException e) {
            System.out.println("Le client s'est déconnecté brutalement.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}