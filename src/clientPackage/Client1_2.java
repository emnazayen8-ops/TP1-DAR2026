package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client1_2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connecté au serveur.");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            int x;
            do {
                System.out.print("Entrez un entier (0 pour quitter) : ");
                x = sc.nextInt();               // (a) saisie clavier
                out.writeInt(x);                // (b) envoi au serveur
                out.flush();
                int resultat = in.readInt();    // (f) réception du résultat
                System.out.println(x + " x 5 = " + resultat);
            } while (x != 0);

            System.out.println("Fermeture de la connexion.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}