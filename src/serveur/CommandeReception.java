/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import bean.ItemsAvailable;
import bean.PlatCommande;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.PlatCommandeFacade;

/**
 *
 * @author Ghassan
 */
public class CommandeReception extends Thread{

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    private PlatCommandeFacade commandeFacade = new PlatCommandeFacade();
    
    List<ItemsAvailable> itemsAvailables = new ArrayList<>();
    
    public CommandeReception(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            itemsAvailables = (List<ItemsAvailable>) ois.readObject();
            
            PlatCommande platCommande = commandeFacade.createCommande(itemsAvailables);
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(platCommande);
            oos.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CommandeReception.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
