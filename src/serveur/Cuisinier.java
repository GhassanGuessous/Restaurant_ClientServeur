/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import bean.PlatCommande;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Ghassan
 */
public class Cuisinier {

    public static void createServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            Connexion connexionThread = new Connexion(serverSocket);
            connexionThread.start();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    
    public static void createServerSocketFacture(PlatCommande platCommande){
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            RechercheItemsFacture itemsFactureThread = new RechercheItemsFacture(platCommande, serverSocket);
            itemsFactureThread.start();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    
    
}
