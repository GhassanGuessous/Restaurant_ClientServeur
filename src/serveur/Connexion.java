/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import bean.ItemsAvailable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ItemsAvailableFacade;

/**
 *
 * @author Ghassan
 */
public class Connexion extends Thread{

    private ServerSocket serverSocket;
    private ObjectOutputStream oos;
    
    private ItemsAvailableFacade availableFacade = new ItemsAvailableFacade();
    private List<ItemsAvailable> itemsAvailables = new ArrayList<>();

    public Connexion(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            itemsAvailables = availableFacade.findAll();
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(itemsAvailables);
            oos.flush();
            
            Recherche rechercheThread = new Recherche(socket);
            CommandeReception receptionThread = new CommandeReception(socket);
            
//            rechercheThread.start();
//            rechercheThread.join();
            
            receptionThread.start();
            receptionThread.join();
        } catch (IOException ex) {
            ex.getStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
