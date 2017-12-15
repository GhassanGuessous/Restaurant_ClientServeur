/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import bean.ItemsAvailable;
import helper.ItemsAvailableFxHelper;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveur.Recherche;

/**
 *
 * @author Ghassan
 */
public class Reception extends Thread{

    private Socket socket;
    private ObjectInputStream oos;
    private ItemsAvailableFxHelper availableFxHelper;
    private List<ItemsAvailable> itemsAvailables;

    public Reception(Socket socket, ItemsAvailableFxHelper availableFxHelper) {
        this.socket = socket;
        this.availableFxHelper = availableFxHelper;
    }
    
    @Override
    public void run() {
        try {
            oos = new ObjectInputStream(socket.getInputStream());
            itemsAvailables = (List<ItemsAvailable>) oos.readObject();
            availableFxHelper.setList(itemsAvailables);
        } catch (IOException ex) {
            ex.getStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
