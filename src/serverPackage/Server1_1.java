package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1_1 {
    public static void main(String[] args) {
        try {
            // Première étape : créer le ServerSocket sur le port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Je suis un serveur en attente la connexion d'un client ");

            // Deuxième étape : accept() BLOQUE jusqu'à l'arrivée d'un client
            Socket socket = serverSocket.accept();
            System.out.println("un client est connecté");

            // Dernière étape : fermer les sockets
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
