/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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

/**
 *
 * @author Ghassan
 */
public class CommandeEmission extends Thread{
    
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    private List<ItemsAvailable> commandeItemses;
    private PlatCommande platCommande;

    public CommandeEmission(Socket socket, List<ItemsAvailable> commandeItemses, PlatCommande platCommande) {
        this.socket = socket;
        this.commandeItemses = commandeItemses;
        this.platCommande = platCommande;
    }
    
    @Override
    public void run() {
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(commandeItemses);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(CommandeEmission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PlatCommande getPlatCommande() {
        return platCommande;
    }

    public void setPlatCommande(PlatCommande platCommande) {
        this.platCommande = platCommande;
    }
}
