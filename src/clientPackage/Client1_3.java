package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("IP du serveur  : ");
        String ipServeur = sc.nextLine().trim();
        int port = 1234;

        try (Socket socket = new Socket()) {
            // connexion avec un délai maximal de 5 secondes
            socket.connect(new InetSocketAddress(ipServeur, port), 5000);
            System.out.println("Connecté à " + ipServeur + ":" + port);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            int choix;
            do {
                System.out.println("\n===== CALCULATRICE =====");
                System.out.println("1. Addition");
                System.out.println("2. Soustraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = sc.nextInt();

                out.writeInt(choix);
                out.flush();
                if (choix == 0) break;

                System.out.print("x = ");
                double x = sc.nextDouble();
                System.out.print("y = ");
                double y = sc.nextDouble();

                out.writeDouble(x);
                out.writeDouble(y);
                out.flush();

                System.out.println(in.readUTF());   // résultat envoyé par le serveur
            } while (true);

            System.out.println("Au revoir !");

        } catch (IOException e) {
            System.out.println("Impossible de joindre le serveur : " + e.getMessage());
        }
    }
}