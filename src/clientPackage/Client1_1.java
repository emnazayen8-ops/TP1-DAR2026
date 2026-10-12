package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class Client1_1 {
    public static void main(String[] args) {
        try {
            // Première étape : le client n'est pas encore connecté
            System.out.println("Je suis un client pas encore connecté…");

            // Connexion au serveur (adresse + port)
            Socket socket = new Socket("localhost", 1234);
            System.out.println("je suis un client connecté");

            // Dernière étape : fermer le socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}